module.exports = carat = {
    echo: function (name, successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "Echo", "echo", [name]);
    }
};