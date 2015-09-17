var cordova = require("cordova");
var exec = require("cordova/exec");

var carat = {
	echo: function(name, success, error) {
        cordova.exec(success,
        	error,
        	"Echo",
        	"echo",
        	[name]);
	}
};

module.exports = carat;