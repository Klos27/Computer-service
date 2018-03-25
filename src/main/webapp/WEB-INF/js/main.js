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
