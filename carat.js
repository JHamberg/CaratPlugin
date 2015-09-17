module.exports = {
	echo: function(name, success, error) {
		var exec = require("cordova/exec");
        cordova.exec(success,
        	error,
        	"CaratPlugin",
        	"echo",
        	[name]);
	}
};