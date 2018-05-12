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
    
});
