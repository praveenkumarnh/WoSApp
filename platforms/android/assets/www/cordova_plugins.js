cordova.define('cordova/plugin_list', function(require, exports, module) {
module.exports = [
    {
        "file": "plugins/org.apache.cordova.plugin.sms/www/sms.js",
        "id": "org.apache.cordova.plugin.sms.Sms",
        "clobbers": [
            "window.sms"
        ]
    },
    {
        "file": "plugins/com.red_folder.phonegap.plugin.backgroundservice/www/backgroundService.js",
        "id": "com.red_folder.phonegap.plugin.backgroundservice.BackgroundService"
    },
    {
        "file": "plugins/com.red_folder.phonegap.plugin.backgroundservice.sample/www/myService.js",
        "id": "com.red_folder.phonegap.plugin.backgroundservice.sample.MyService",
        "clobbers": [
            "cordova.plugins.myService"
        ]
    }
];
module.exports.metadata = 
// TOP OF METADATA
{
    "org.apache.cordova.plugin.sms": "0.1.0",
    "com.red_folder.phonegap.plugin.backgroundservice": "2.0.0",
    "com.red_folder.phonegap.plugin.backgroundservice.sample": "2.0.0"
}
// BOTTOM OF METADATA
});