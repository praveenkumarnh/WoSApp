
var app = {
    SOME_CONSTANTS : false,  // some constant

    initialize: function() {
        console.log("console log init");
        this.bindEvents();
        this.initFastClick();
    },
    
    bindEvents: function() {
        document.addEventListener('deviceready', this.onDeviceReady, false);
    },
    initFastClick : function() {
        window.addEventListener('load', function() {
            FastClick.attach(document.body);
        }, false);
    },


    onDeviceReady: function() {
        console.log("device ready, start making you custom calls!");

        //Moar code

    }
};