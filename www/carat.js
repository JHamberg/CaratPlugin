var carat = {
    echo: function (name, successCallback, errorCallback) {
        cordova.exec(
        	successCallback,
        	errorCallback,
        	"Echo",
        	"echo",
        	[name]);
    }
};

module.exports = carat;