package no.nordicsemi.android.nrfmesh.node;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.slider.Slider;
import com.google.android.material.snackbar.Snackbar;

import dagger.hilt.android.AndroidEntryPoint;
import no.nordicsemi.android.mesh.ApplicationKey;
import no.nordicsemi.android.mesh.models.LightLcServer;
import no.nordicsemi.android.mesh.sensorutils.DeviceProperty;
import no.nordicsemi.android.mesh.sensorutils.DevicePropertyCharacteristic;
import no.nordicsemi.android.mesh.sensorutils.TimeHour24;
import no.nordicsemi.android.mesh.sensorutils.TimeMillisecond24;
import no.nordicsemi.android.mesh.sensorutils.TimeSecond;
import no.nordicsemi.android.mesh.transport.Element;
import no.nordicsemi.android.mesh.transport.LightLCModeGet;
import no.nordicsemi.android.mesh.transport.LightLCModeSet;
import no.nordicsemi.android.mesh.transport.LightLCModeStatus;
import no.nordicsemi.android.mesh.transport.LightLCPropertyGet;
import no.nordicsemi.android.mesh.transport.LightLCPropertySet;
import no.nordicsemi.android.mesh.transport.LightLCPropertyStatus;
import no.nordicsemi.android.mesh.transport.MeshMessage;
import no.nordicsemi.android.mesh.transport.MeshModel;
import no.nordicsemi.android.mesh.transport.ProvisionedMeshNode;
import no.nordicsemi.android.mesh.utils.MeshAddress;
import no.nordicsemi.android.nrfmesh.R;
import no.nordicsemi.android.nrfmesh.databinding.LayoutLightLcServerBinding;

@AndroidEntryPoint
public class LightLcServerActivity extends ModelConfigurationActivity {

    private static final String TAG = LightLcServerActivity.class.getSimpleName();

    private TextView modeState;
    private Button mActionModeOnOff, mActionReadTimeOn, mActionReadTimeProlong, mActionSendTimeOn, mActionSendTimeProlong;
    protected int mTimeOnDuration, mTimeProlongDuration;

    private int fromSlideToSeconds(int progress) {
        if (progress >= 0 && progress <= 120) {
            return progress;
        } if (progress >= 121) {
            return (progress - 120) * 60 + 120;
        }
        return 0;
    }

    private int fromSecondsToSlide(int seconds) {
        if(seconds <= 120)
            return seconds;
        return (seconds - 120) / 60 + 120;
    }

    private String fromSecondsToLabel(int seconds) {
        if(seconds <= 120)
            return getString(R.string.transition_time_interval, String.valueOf(seconds), "s");
        return getString(R.string.transition_time_interval, String.valueOf(seconds / 60), "min");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSwipe.setOnRefreshListener(this);
        final MeshModel model = mViewModel.getSelectedModel().getValue();
        if (model instanceof LightLcServer) {
            final LayoutLightLcServerBinding nodeControlsContainer = LayoutLightLcServerBinding.inflate(getLayoutInflater(), binding.nodeControlsContainer, true);

            modeState = nodeControlsContainer.onOffState;

            final Slider timeOnSlider = nodeControlsContainer.timeOnSlider;
            timeOnSlider.setValueFrom(0);
            timeOnSlider.setValueTo(230);
            timeOnSlider.setValue(0);
            timeOnSlider.setStepSize(1);
            final TextView timeOn = nodeControlsContainer.timeOn;

            final Slider timeProlongSlider = nodeControlsContainer.timeProlongSlider;
            timeProlongSlider.setValueFrom(0);
            timeProlongSlider.setValueTo(255);
            timeProlongSlider.setValue(0);
            timeProlongSlider.setStepSize(1);
            final TextView timeProlong = nodeControlsContainer.timeProlong;

            mActionModeOnOff = nodeControlsContainer.actionOn;
            mActionModeOnOff.setOnClickListener(v -> {
                try {
                    sendLcModeOnOff(mActionModeOnOff.getText().toString().equals(getString(R.string.action_generic_on)));
                } catch (IllegalArgumentException ex) {
                    mViewModel.displaySnackBar(this, mContainer, ex.getMessage(), Snackbar.LENGTH_LONG);
                }
            });

            mActionRead = nodeControlsContainer.actionRead;
            mActionRead.setOnClickListener(v -> sendLightLcModeGet());

            mActionReadTimeOn = nodeControlsContainer.actionReadTimeon;
            mActionReadTimeOn.setOnClickListener(v -> {
                try {
                    sendLCGetProperty(DeviceProperty.LIGHT_CONTROL_TIME_RUN_ON);
                } catch (IllegalArgumentException ex) {
                    mViewModel.displaySnackBar(this, mContainer, ex.getMessage(), Snackbar.LENGTH_LONG);
                }
            });

            mActionSendTimeOn = nodeControlsContainer.actionWriteTimeOn;
            mActionSendTimeOn.setOnClickListener(v -> {
                try {
                    sendLCSetProperty(DeviceProperty.LIGHT_CONTROL_TIME_RUN_ON, new TimeMillisecond24(mTimeOnDuration));
                } catch (IllegalArgumentException ex) {
                    mViewModel.displaySnackBar(this, mContainer, ex.getMessage(), Snackbar.LENGTH_LONG);
                }
            });

            mActionReadTimeProlong = nodeControlsContainer.actionReadTimeProlong;
            mActionReadTimeProlong.setOnClickListener(v -> {
                try {
                    sendLCGetProperty(DeviceProperty.LIGHT_CONTROL_TIME_PROLONG);
                } catch (IllegalArgumentException ex) {
                    mViewModel.displaySnackBar(this, mContainer, ex.getMessage(), Snackbar.LENGTH_LONG);
                }
            });

            mActionSendTimeProlong = nodeControlsContainer.actionWriteTimeProlong;
            mActionSendTimeProlong.setOnClickListener(v -> {
                try {
                    sendLCSetProperty(DeviceProperty.LIGHT_CONTROL_TIME_PROLONG, new TimeMillisecond24(mTimeProlongDuration));
                } catch (IllegalArgumentException ex) {
                    mViewModel.displaySnackBar(this, mContainer, ex.getMessage(), Snackbar.LENGTH_LONG);
                }
            });

            timeOnSlider.addOnChangeListener(new Slider.OnChangeListener() {
                @Override
                public void onValueChange(@NonNull final Slider slider, final float value, final boolean fromUser) {
                    mTimeOnDuration = fromSlideToSeconds((int)value);
                    timeOn.setText(fromSecondsToLabel(mTimeOnDuration));
                }
            });

            timeProlongSlider.addOnChangeListener(new Slider.OnChangeListener() {
                @Override
                public void onValueChange(@NonNull final Slider slider, final float value, final boolean fromUser) {
                    mTimeProlongDuration = fromSlideToSeconds((int)value);
                    timeProlong.setText(fromSecondsToLabel(mTimeProlongDuration));
                }
            });

            mViewModel.getSelectedModel().observe(this, meshModel -> {
                if (meshModel != null) {
                    updateAppStatusUi(meshModel);
                    updatePublicationUi(meshModel);
                    updateSubscriptionUi(meshModel);
                }
            });
        }
    }

    @Override
    protected void enableClickableViews() {
        super.enableClickableViews();
        if (mActionModeOnOff != null && !mActionModeOnOff.isEnabled())
            mActionModeOnOff.setEnabled(true);
    }

    @Override
    protected void disableClickableViews() {
        super.disableClickableViews();
        if (mActionModeOnOff != null)
            mActionModeOnOff.setEnabled(false);
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
    }

    /**
     * Send light lc set property to mesh node
     *
     * @param property
     * @param value
     */
    public void sendLCSetProperty(DeviceProperty property, DevicePropertyCharacteristic<?> value) {
        if (!checkConnectivity(mContainer)) return;
        final Element element = mViewModel.getSelectedElement().getValue();
        if (element != null) {
            final MeshModel model = mViewModel.getSelectedModel().getValue();
            if (model != null) {
                if (!model.getBoundAppKeyIndexes().isEmpty()) {
                    final int appKeyIndex = model.getBoundAppKeyIndexes().get(0);
                    final ApplicationKey appKey = mViewModel.getNetworkLiveData().getMeshNetwork().getAppKey(appKeyIndex);

                    final int address = element.getElementAddress();
                    Log.v(TAG, "Sending message to element's unicast address: " + MeshAddress.formatAddress(address, true));

                    final LightLCPropertySet lightLCPropertySet = new LightLCPropertySet(appKey, property, value);
                    sendAcknowledgedMessage(address, lightLCPropertySet);
                } else {
                    mViewModel.displaySnackBar(this, mContainer, getString(R.string.error_no_app_keys_bound), Snackbar.LENGTH_LONG);
                }
            }
        }
    }

    /**
     * Send light lc mode on off to mesh node
     *
     * @param state true to turn on and false to turn off
     */
    public void sendLcModeOnOff(final boolean state) {
        if (!checkConnectivity(mContainer)) return;
        final ProvisionedMeshNode node = mViewModel.getSelectedMeshNode().getValue();
        if (node != null) {
            final Element element = mViewModel.getSelectedElement().getValue();
            if (element != null) {
                final MeshModel model = mViewModel.getSelectedModel().getValue();
                if (model != null) {
                    if (!model.getBoundAppKeyIndexes().isEmpty()) {
                        final int appKeyIndex = model.getBoundAppKeyIndexes().get(0);
                        final ApplicationKey appKey = mViewModel.getNetworkLiveData().getMeshNetwork().getAppKey(appKeyIndex);

                        final int address = element.getElementAddress();
                        Log.v(TAG, "Sending message to element's unicast address: " + MeshAddress.formatAddress(address, true));

                        final LightLCModeSet lightLCModeSet = new LightLCModeSet(appKey, state);
                        sendAcknowledgedMessage(address, lightLCModeSet);
                    } else {
                        mViewModel.displaySnackBar(this, mContainer, getString(R.string.error_no_app_keys_bound), Snackbar.LENGTH_LONG);
                    }
                }
            }
        }
    }

    /**
     * Send light lc read property to mesh node
     *
     * @param property property to read
     */
    public void sendLCGetProperty(DeviceProperty property) {
        if (!checkConnectivity(mContainer)) return;
        final Element element = mViewModel.getSelectedElement().getValue();
        if (element != null) {
            final MeshModel model = mViewModel.getSelectedModel().getValue();
            if (model != null) {
                if (!model.getBoundAppKeyIndexes().isEmpty()) {
                    final int appKeyIndex = model.getBoundAppKeyIndexes().get(0);
                    final ApplicationKey appKey = mViewModel.getNetworkLiveData().getMeshNetwork().getAppKey(appKeyIndex);

                    final int address = element.getElementAddress();
                    Log.v(TAG, "Sending message to element's unicast address: " + MeshAddress.formatAddress(address, true));

                    final LightLCPropertyGet lightLCPropertyGet = new LightLCPropertyGet(appKey, property);
                    sendAcknowledgedMessage(address, lightLCPropertyGet);
                } else {
                    mViewModel.displaySnackBar(this, mContainer, getString(R.string.error_no_app_keys_bound), Snackbar.LENGTH_LONG);
                }
            }
        }
    }

    @Override
    protected void updateMeshMessage(final MeshMessage meshMessage) {
        super.updateMeshMessage(meshMessage);
        mSwipe.setOnRefreshListener(this);
        if (meshMessage instanceof LightLCModeStatus) {
            final LightLCModeStatus status = (LightLCModeStatus) meshMessage;
            if(status.getStatus()) {
                modeState.setText(R.string.action_generic_on);
                mActionModeOnOff.setText(R.string.action_generic_off);
            } else {
                modeState.setText(R.string.action_generic_off);
                mActionModeOnOff.setText(R.string.action_generic_on);
            }
        } else if(meshMessage instanceof LightLCPropertyStatus) {
            final LightLCPropertyStatus status = (LightLCPropertyStatus) meshMessage;
            final DeviceProperty property = status.getProperty();
            if(property == DeviceProperty.LIGHT_CONTROL_TIME_RUN_ON) {
                TimeMillisecond24 characteristic = (TimeMillisecond24)status.getValue();
                mTimeOnDuration = characteristic.getValue().intValue();
                ((TextView) findViewById(R.id.time_on)).setText(fromSecondsToLabel(mTimeOnDuration));
                ((Slider) findViewById(R.id.time_on_slider)).setValue(fromSecondsToSlide(mTimeOnDuration));
            } else if(property == DeviceProperty.LIGHT_CONTROL_TIME_PROLONG) {
                TimeMillisecond24 characteristic = (TimeMillisecond24)status.getValue();
                mTimeProlongDuration = characteristic.getValue().intValue();
                ((TextView) findViewById(R.id.time_prolong)).setText(fromSecondsToLabel(mTimeProlongDuration));
                ((Slider) findViewById(R.id.time_prolong_slider)).setValue(fromSecondsToSlide(mTimeProlongDuration));
            }
        }
        hideProgressBar();
    }

    /**
     * Send light lc mode get to mesh node
     */
    public void sendLightLcModeGet() {
        if (!checkConnectivity(mContainer)) return;
        final Element element = mViewModel.getSelectedElement().getValue();
        if (element != null) {
            final MeshModel model = mViewModel.getSelectedModel().getValue();
            if (model != null) {
                if (!model.getBoundAppKeyIndexes().isEmpty()) {
                    final int appKeyIndex = model.getBoundAppKeyIndexes().get(0);
                    final ApplicationKey appKey = mViewModel.getNetworkLiveData().getMeshNetwork().getAppKey(appKeyIndex);

                    final int address = element.getElementAddress();
                    Log.v(TAG, "Sending message to element's unicast address: " + MeshAddress.formatAddress(address, true));

                    final LightLCModeGet lightLCModeGet = new LightLCModeGet(appKey);
                    sendAcknowledgedMessage(address, lightLCModeGet);
                } else {
                    mViewModel.displaySnackBar(this, mContainer, getString(R.string.error_no_app_keys_bound), Snackbar.LENGTH_LONG);
                }
            }
        }
    }
}
