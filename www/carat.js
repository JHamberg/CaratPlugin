var exec = require("cordova/exec");

var Carat = {
	echo: function(name, successCallback, errorCallback) {
        cordova.exec(
        	successCallback,
        	errorCallback,
        	"Echo",
        	"echo",
        	[name]);
	}
};

module.exports = Carat;