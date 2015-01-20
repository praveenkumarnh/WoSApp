
var AppSettings = new Object();

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

    startSequence: function() {
    alert("sending sms");
        var intent = '';
        var success = function () { alert('Message sent successfully'); };
        var error = function (e) { alert('Message Failed:' + e); };
        sms.send("+917760118257", "testing WoSApp", intent, success, error); //"9980792858"
    alert("sms sent");
    },


    onDeviceReady: function() {
        console.log("device ready, start making you custom calls!");
		
		checkFirstRun();
        //Moar code

    }

   
};

function checkFirstRun()
{
	if(typeof( window.localStorage.WoSApp) == "undefined")
	{
		//go to config page
		window.location= "#firstrun-page";
		//populate details into settings var
		
		window.localStorage.setItem("WoSApp",AppSettings);
	}
}

var myShakeEvent = new Shake({
    threshold: 15, // optional shake strength threshold
    timeout: 1000 // optional, determines the frequency of event generation
});

myShakeEvent.start();
var shaking = false;

window.addEventListener('shake', shakeEventDidOccur, false);
document.addEventListener("volumedownbutton", onVolumeDownKeyDown, false);

//function to call when shake occurs
function shakeEventDidOccur () {
    shaking = true;
    setTimeout( function() { shaking = false;} , 1000 );
}


    
function onVolumeDownKeyDown() {
         //put your own code here etc.
    if(shaking)
        alert('shake+volume!!');
}