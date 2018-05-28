$(document).ready(function () {
	$('.alert').alert()

    $("#register-form").submit(function (e) {
        e.preventDefault();

    	var credentials = {
    		first_name: $('#first_name').val(),
    		last_name: $('#last_name').val(),
    		password: $('#password').val(),
    		password2: $('#password2').val(),
    		email: $('#email').val()
    	}

        $.post( "register", credentials)
  		.done(function() {
  			openModal('Congratulations!', 'You have succesfully registered in our system.', '/auth/login');
  		})
  		.fail(function(data) {
  			showError(data.responseText);
  		})

    });
	
    $("#login-form").submit(function (e) {
        e.preventDefault();

    	var credentials = {
    		email: $('#email').val(),
    		password: $('#password').val(),
    	}

        $.post( "login", credentials)
  		.done(function() {
  			window.location.href = '/user/profile';
  		})
  		.fail(function(data) {
  			showError(data.responseText);
  		})

    });
    
    $("#change-request").submit(function (e) {
        e.preventDefault();

    	var credentials = {
    		empid: $('#empid').val(),
    		reqid: $('#reqid').val(),
    	}

        $.post( "change-request", credentials)
  		.done(function() {
  			openModal('Congratulations!', 'You have succesfully changed the request.', '/service/change-request');
  		})
  		.fail(function(data) {
  			showError(data.responseText);
  		})

    });
    
    $("#add-request-form").submit(function (e) {
        e.preventDefault();

    	var credentials = {
    		computer_producer: $('#computer_producer').val(),
    		computer_serial: $('#computer_serial').val(),
    		computer_description: $('#computer_description').val()
    	}

        $.post( "/user/new-request", credentials)
  		.done(function() {
  			openModal('Congratulations!', 'You have succesfully added service request in our system.', '/user/new-request');
  		})
  		.fail(function(data) {
  			showError(data.responseText);
  		})

    });
    
    $("#change-details-form").submit(function (e) {
        e.preventDefault();

    	var credentials = {
    		first_name: $('#first_name').val(),
    		last_name: $('#last_name').val(),
    		address: $('#address').val(),
    		phone: $('#phone').val(),
    	}

        $.post( "change-details", credentials)
  		.done(function() {
  			openModal('Congratulations!', 'You have succesfully changed your details.', '/user/profile');
  		})
  		.fail(function(data) {
  			showError('Something went wrong!');
  		})

    });
    
    $("#change-password-form").submit(function (e) {
        e.preventDefault();

    	var credentials = {
    		password: $('#password').val(),
    		new_password: $('#new_password').val()
    	}

        $.post( "change-password", credentials)
  		.done(function() {
  			openModal('Congratulations!', 'You have succesfully changed your password.', '/user/profile');
  		})
  		.fail(function(data) {
  			showError(data.responseText);
  		})

    });
    
    $("#chat-form").submit(function (e) {
        e.preventDefault();

    	var credentials = {
    		id_user: $('#id_user').val(),
    		id_service_request: $('#id_service_request').val(),
    		content: $('#content').val(),
    	}

        $.post( "/user/chat", credentials)
  		.done(function() {
  			$('#content').val('');
  		})
  		.fail(function(data) {
  			showError(data.responseText);
  		})

    });
    
          
    $("#forgot-password-form").submit(function (e) {
        e.preventDefault();

    	var credentials = {
    		email: $('#email').val()
    	}

        $.post( "forgot-password", credentials)
  		.done(function() {
  			openModal('Congratulations!', 'Email has been sent!', '/welcome');
  		})
  		.fail(function(data) {
  			showError(data.responseText);
  		})

    });          
          
    function getChatMessages() { 
        if (
        	(window.location.pathname == '/user/existing-requests' && window.location.search[4]) ||
        	(window.location.pathname == '/service/existing-requests/edit' && window.location.search[11])
        	) {
        	
        	$.get( "/user/chat", { id_service_request: $('#id_service_request').val() })
      		.done(function(data) {

      			const messages = data;      			
      			let chat = '';
      			
      			for(let i = 0; i < messages.length; i++) {
      				chat += '<span>' + messages[i].last_name + ' ' + messages[i].first_name + ': ' + messages[i].content + '</span><br/>';
      			}
      			
      			$('#chat_container').text('');
  				$('#chat_container').append(chat);
  				
      		})
      		.fail(function(data) {
      			console.log('sth went wrong with getChatMessagess()');
      		})

        	setTimeout(() => getChatMessages(), 1000);
        }
    }
    
    function openModal(title, body, redirect = 'default'){
    	$('#myModal .modal-title').html(title);
    	$('#myModal .modal-body').html(body);

    	$('#myModal').modal({
    	    backdrop    : 'static'
    	});
    	
    	$('#myModal').on('hidden.bs.modal', function (e) {
    		if(redirect != 'default') {
                window.location.href = redirect;
    		}
    	});
    }
    
    function showError(error) {
    	$('#error').html('<div class="alert alert-danger alert-dismissible fade show text-center" role="alert"><strong>Warning!</strong> <span>'+ error +'</span><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button></div>');
    }
    
    getChatMessages();
    
});
