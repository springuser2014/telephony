
Telephony.Auth = {
    getSessionObj : function() {

        var sessId = $.cookie('sessionId');
        var username = $.cookie('username');
        var validity = $.cookie('validity');

        if (sessionId != undefined && username != undefined) {
            return {
                username : username,
                sessionId : sessId,
                validity : validity
            };
        } else {
            return { };
        }
    },

    getAuthObj : function() {

        var sessionId = $.cookie('sessionId');
        var username = $.cookie('username');

        if (sessionId != undefined && username != undefined) {
            return {
                username : username,
                sessionId : sessionId
            };
        } else {
            return { };
        }
    },

    setSessionObj : function(obj) {

        var sessionId = $.cookie('sessionId', obj.session.sessionId);
        var username = $.cookie('username', obj.session.username);
        var validity = $.cookie('validity', obj.session.validity);

        if (sessionId != undefined && username != undefined) {
            return {
                username : username,
                sessionId : sessionId,
                validity : validity
            };
        } else {
            return { };
        }
    },

    authorize : function(username, password) {

        Telephony.Rest.Session.Initialize({
            username : username,
            password : password
        },
        function(jqXHR, status) {
            var resp = jqXHR.responseJSON;

            if (resp.success) {
                Telephony.Auth.setSessionObj(resp);
            }
        },
        function() { },
        false);
    }

};

