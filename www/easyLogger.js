var exec = require('cordova/exec');

exports.saveLog = function (arg0, success, error) {
    exec(success, error, 'easyLogger','configure',[arg0]);
};


