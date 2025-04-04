/*
 * Copyright (c) 2018, Nordic Semiconductor
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its contributors may be used to endorse or promote products derived from this
 * software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE
 * USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package no.nordicsemi.android.mesh.models;

@SuppressWarnings("WeakerAccess")
public class SigModelParser {

    public static final short CONFIGURATION_SERVER = 0x0000;
    public static final short CONFIGURATION_CLIENT = 0x0001;
    private static final short HEALTH_SERVER_MODEL = 0x0002;
    private static final short HEALTH_CLIENT_MODEL = 0x0003;

    private static final short REMOTE_PROVISIONING_SERVER                 = 0x0004;
    private static final short REMOTE_PROVISIONING_CLIENT                 = 0x0005;
    private static final short MESH_PRIVATE_BEACON_SERVER                 = 0x000A;
    private static final short MESH_PRIVATE_BEACON_CLIENT                 = 0x000B;
    private static final short ON_DEMAND_PRIVATE_PROXY_SERVER             = 0x000C;
    private static final short ON_DEMAND_PRIVATE_PROXY_CLIENT             = 0x000D;
    private static final short SAR_CONFIGURATION_SERVER                   = 0x000E;
    private static final short SAR_CONFIGURATION_CLIENT                   = 0x000F;
    private static final short OPCODES_AGGREGATOR_SERVER                  = 0x0010;
    private static final short OPCODES_AGGREGATOR_CLIENT                  = 0x0011;
    private static final short LARGE_COMPOSITION_DATA_SERVER              = 0x0012;
    private static final short LARGE_COMPOSITION_DATA_CLIENT              = 0x0013;
    private static final short SOLICITATION_PDU_RPL_CONFIGURATION_SERVER  = 0x0014;
    private static final short SOLICITATION_PDU_RPL_CONFIGURATION_CLIENT  = 0x0015;

    public static final short GENERIC_ON_OFF_SERVER = 0x1000;
    public static final short GENERIC_ON_OFF_CLIENT = 0x1001;
    public static final short GENERIC_LEVEL_SERVER = 0x1002;
    public static final short GENERIC_LEVEL_CLIENT = 0x1003;

    private static final short GENERIC_DEFAULT_TRANSITION_TIME_SERVER = 0x1004;
    private static final short GENERIC_DEFAULT_TRANSITION_TIME_CLIENT = 0x1005;
    private static final short GENERIC_POWER_ON_OFF_SERVER = 0x1006;
    private static final short GENERIC_POWER_ON_OFF_SETUP_SERVER = 0x1007;
    private static final short GENERIC_POWER_ON_OFF_CLIENT = 0x1008;
    private static final short GENERIC_POWER_LEVEL_SERVER = 0x1009;
    private static final short GENERIC_POWER_LEVEL_SETUP_SERVER = 0x100A;
    private static final short GENERIC_POWER_LEVEL_CLIENT = 0x100B;
    private static final short GENERIC_BATTERY_SERVER = 0x100C;
    private static final short GENERIC_BATTERY_CLIENT = 0x100D;
    private static final short GENERIC_LOCATION_SERVER = 0x100E;
    private static final short GENERIC_LOCATION_SETUP_SERVER = 0x100F;
    private static final short GENERIC_LOCATION_CLIENT = 0x1010;
    private static final short GENERIC_ADMIN_PROPERTY_SERVER = 0x1011;
    private static final short GENERIC_MANUFACTURER_PROPERTY_SERVER = 0x1012;
    private static final short GENERIC_USER_PROPERTY_SERVER = 0x1013;
    private static final short GENERIC_CLIENT_PROPERTY_SERVER = 0x1014;
    private static final short GENERIC_PROPERTY_CLIENT = 0x1015;

    // SIG Sensors, Mesh Model Spec
    public static final short SENSOR_SERVER = 0x1100;
    public static final short SENSOR_SETUP_SERVER = 0x1101;
    public static final short SENSOR_CLIENT = 0x1102;

    //SIG Time and Scene, Mesh Model Spec;
    private static final short TIME_SERVER = 0x1200;
    private static final short TIME_SETUP_SERVER = 0x1201;
    private static final short TIME_CLIENT = 0x1202;
    public static final short SCENE_SERVER = 0x1203;
    public static final short SCENE_SETUP_SERVER = 0x1204;
    private static final short SCENE_CLIENT = 0x1205;
    private static final short SCHEDULER_SERVER = 0x1206;
    private static final short SCHEDULER_SETUP_SERVER = 0x1207;
    private static final short SCHEDULER_CLIENT = 0x1208;

    // SIG Lightning, Mesh Model Spec
    private static final short LIGHT_LIGHTNESS_SERVER = 0x1300;
    private static final short LIGHT_LIGHTNESS_SETUP_SERVER = 0x1301;
    private static final short LIGHT_LIGHTNESS_CLIENT = 0x1302;
    private static final short LIGHT_CTL_SERVER = 0x1303;
    private static final short LIGHT_CTL_SETUP_SERVER = 0x1304;
    private static final short LIGHT_CTL_CLIENT = 0x1305;
    private static final short LIGHT_CTL_TEMPERATURE_SERVER = 0x1306;
    private static final short LIGHT_HSL_SERVER = 0x1307;
    private static final short LIGHT_HSL_SETUP_SERVER = 0x1308;
    private static final short LIGHT_HSL_CLIENT = 0x1309;
    private static final short LIGHT_HSL_HUE_SERVER = 0x130A;
    private static final short LIGHT_HSL_SATURATION_SERVER = 0x130B;
    private static final short LIGHT_XYL_SERVER = 0x130C;
    private static final short LIGHT_XYL_SETUP_SERVER = 0x130D;
    private static final short LIGHT_XYL_CLIENT = 0x130E;
    public static final short LIGHT_LC_SERVER = 0x130F;
    private static final short LIGHT_LC_SETUP_SERVER = 0x1310;
    private static final short LIGHT_LC_CLIENT = 0x1311;

    // BLOB
    private static final short BLOB_TRANSFER_SERVER                       = 0x1400;
    private static final short BLOB_TRANSFER_CLIENT                       = 0x1401;

    // Firmware Update
    private static final short FIRMWARE_UPDATE_SERVER                     = 0x1402;
    private static final short FIRMWARE_UPDATE_CLIENT                     = 0x1403;
    private static final short FIRMWARE_DISTRIBUTION_SERVER               = 0x1404;
    private static final short FIRMWARE_DISTRIBUTION_CLIENT               = 0x1405;

    /**
     * Returns the Bluetooth sig model based on the model id.
     *
     * @param modelId bluetooth sig model id
     * @return SigModel
     */
    public static SigModel getSigModel(final int modelId) {
        switch (modelId) {
            case CONFIGURATION_SERVER:
                return new ConfigurationServerModel(modelId);
            case CONFIGURATION_CLIENT:
                return new ConfigurationClientModel(modelId);
            case HEALTH_SERVER_MODEL:
                return new HealthServerModel(modelId);
            case HEALTH_CLIENT_MODEL:
                return new HealthClientModel(modelId);
            case GENERIC_ON_OFF_SERVER:
                return new GenericOnOffServerModel(modelId);
            case GENERIC_ON_OFF_CLIENT:
                return new GenericOnOffClientModel(modelId);
            case GENERIC_LEVEL_SERVER:
                return new GenericLevelServerModel(modelId);
            case GENERIC_LEVEL_CLIENT:
                return new GenericLevelClientModel(modelId);
            case GENERIC_DEFAULT_TRANSITION_TIME_SERVER:
                return new GenericDefaultTransitionTimeServer(modelId);
            case GENERIC_DEFAULT_TRANSITION_TIME_CLIENT:
                return new GenericDefaultTransitionTimeClient(modelId);
            case GENERIC_POWER_ON_OFF_SERVER:
                return new GenericPowerOnOffServer(modelId);
            case GENERIC_POWER_ON_OFF_SETUP_SERVER:
                return new GenericPowerOnOffSetupServer(modelId);
            case GENERIC_POWER_ON_OFF_CLIENT:
                return new GenericPowerOnOffClient(modelId);
            case GENERIC_POWER_LEVEL_SERVER:
                return new GenericPowerLevelServer(modelId);
            case GENERIC_POWER_LEVEL_SETUP_SERVER:
                return new GenericPowerLevelSetupServer(modelId);
            case GENERIC_POWER_LEVEL_CLIENT:
                return new GenericPowerLevelClient(modelId);
            case GENERIC_BATTERY_SERVER:
                return new GenericBatteryServer(modelId);
            case GENERIC_BATTERY_CLIENT:
                return new GenericBatteryClient(modelId);
            case GENERIC_LOCATION_SERVER:
                return new GenericLocationServer(modelId);
            case GENERIC_LOCATION_SETUP_SERVER:
                return new GenericLocationSetupServer(modelId);
            case GENERIC_LOCATION_CLIENT:
                return new GenericLocationClient(modelId);
            case GENERIC_ADMIN_PROPERTY_SERVER:
                return new GenericAdminPropertyServer(modelId);
            case GENERIC_MANUFACTURER_PROPERTY_SERVER:
                return new GenericManufacturerPropertyServer(modelId);
            case GENERIC_USER_PROPERTY_SERVER:
                return new GenericUserPropertyServer(modelId);
            case GENERIC_CLIENT_PROPERTY_SERVER:
                return new GenericClientPropertyServer(modelId);
            case GENERIC_PROPERTY_CLIENT:
                return new GenericPropertyClient(modelId);
            case SENSOR_SERVER:
                return new SensorServer(modelId);
            case SENSOR_SETUP_SERVER:
                return new SensorSetupServer(modelId);
            case SENSOR_CLIENT:
                return new SensorClient(modelId);
            case TIME_SERVER:
                return new TimeServer(modelId);
            case TIME_SETUP_SERVER:
                return new TimeSetupServer(modelId);
            case TIME_CLIENT:
                return new TimeClient(modelId);
            case SCENE_SERVER:
                return new SceneServer(modelId);
            case SCENE_SETUP_SERVER:
                return new SceneSetupServer(modelId);
            case SCENE_CLIENT:
                return new SceneClient(modelId);
            case SCHEDULER_SERVER:
                return new SchedulerServer(modelId);
            case SCHEDULER_SETUP_SERVER:
                return new SchedulerSetupServer(modelId);
            case SCHEDULER_CLIENT:
                return new SchedulerClient(modelId);
            case LIGHT_LIGHTNESS_SERVER:
                return new LightLightnessServer(modelId);
            case LIGHT_LIGHTNESS_SETUP_SERVER:
                return new LightLightnessSetupServer(modelId);
            case LIGHT_LIGHTNESS_CLIENT:
                return new LightLightnessClient(modelId);
            case LIGHT_CTL_SERVER:
                return new LightCtlServer(modelId);
            case LIGHT_CTL_SETUP_SERVER:
                return new LightCtlSetupServer(modelId);
            case LIGHT_CTL_CLIENT:
                return new LightCtlClient(modelId);
            case LIGHT_CTL_TEMPERATURE_SERVER:
                return new LightCtlTemperatureServer(modelId);
            case LIGHT_HSL_SERVER:
                return new LightHslServer(modelId);
            case LIGHT_HSL_SETUP_SERVER:
                return new LightHslSetupServer(modelId);
            case LIGHT_HSL_CLIENT:
                return new LightHslClient(modelId);
            case LIGHT_HSL_HUE_SERVER:
                return new LightHslHueServer(modelId);
            case LIGHT_HSL_SATURATION_SERVER:
                return new LightHslSaturationServer(modelId);
            case LIGHT_XYL_SERVER:
                return new LightXylServer(modelId);
            case LIGHT_XYL_SETUP_SERVER:
                return new LightXylSetupServer(modelId);
            case LIGHT_XYL_CLIENT:
                return new LightXylClient(modelId);
            case LIGHT_LC_SERVER:
                return new LightLcServer(modelId);
            case LIGHT_LC_SETUP_SERVER:
                return new LightLcSetupServer(modelId);
            case LIGHT_LC_CLIENT:
                return new LightLcClient(modelId);
            case REMOTE_PROVISIONING_SERVER:
                return new RemoteProvisioningServer(modelId);
            case REMOTE_PROVISIONING_CLIENT:
                return new RemoteProvisioningClient(modelId);
            case MESH_PRIVATE_BEACON_SERVER:
                return new MeshPrivateBeaconServer(modelId);
            case MESH_PRIVATE_BEACON_CLIENT:
                return new MeshPrivateBeaconClient(modelId);
            case ON_DEMAND_PRIVATE_PROXY_SERVER:
                return new OnDemandPrivateProxyServer(modelId);
            case ON_DEMAND_PRIVATE_PROXY_CLIENT:
                return new OnDemandPrivateProxyClient(modelId);
            case SAR_CONFIGURATION_SERVER:
                return new SarConfigurationServer(modelId);
            case SAR_CONFIGURATION_CLIENT:
                return new SarConfigurationClient(modelId);
            case OPCODES_AGGREGATOR_SERVER:
                return new OpcodesAggregatorServer(modelId);
            case OPCODES_AGGREGATOR_CLIENT:
                return new OpcodesAggregatorClient(modelId);
            case LARGE_COMPOSITION_DATA_SERVER:
                return new LargeCompositionDataServer(modelId);
            case LARGE_COMPOSITION_DATA_CLIENT:
                return new LargeCompositionDataClient(modelId);
            case SOLICITATION_PDU_RPL_CONFIGURATION_SERVER:
                return new SolicitationPduRplConfigurationServer(modelId);
            case SOLICITATION_PDU_RPL_CONFIGURATION_CLIENT:
                return new SolicitationPduRplConfigurationClient(modelId);
            case BLOB_TRANSFER_SERVER:
                return new BlobTransferServer(modelId);
            case BLOB_TRANSFER_CLIENT:
                return new BlobTransferClient(modelId);
            case FIRMWARE_UPDATE_SERVER:
                return new FirmwareUpdateServer(modelId);
            case FIRMWARE_UPDATE_CLIENT:
                return new FirmwareUpdateClient(modelId);
            case FIRMWARE_DISTRIBUTION_SERVER:
                return new FirmwareDistributionServer(modelId);
            case FIRMWARE_DISTRIBUTION_CLIENT:
                return new FirmwareDistributionClient(modelId);
            default:
                return new UnknownModel(modelId);
        }
    }
}