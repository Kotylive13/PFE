/*jQuery.extend(jQuery.validator.messages, {
  required: "votre message",
  remote: "votre message",
  email: "votre message",
  url: "votre message",
  date: "votre message",
  dateISO: "votre message",
  number: "votre message",
  digits: "votre message",
  creditcard: "votre message",
  equalTo: "votre message",
  accept: "votre message",
  maxlength: jQuery.validator.format("votre message {0} caractéres."),
  minlength: jQuery.validator.format("votre message {0} caractéres."),
  rangelength: jQuery.validator.format("votre message  entre {0} et {1} caractéres."),
  range: jQuery.validator.format("votre message  entre {0} et {1}."),
  max: jQuery.validator.format("votre message  inférieur ou égal à {0}."),
  min: jQuery.validator.format("votre message  supérieur ou égal à {0}.")
});*/

$( "#updateInformationForm" ).validate({
  errorElement: 'span',
    rules: {
      name: {
          required: true
      },
      firstName: {
          required: true
      }
    }
});

$( "#postPublicationForm" ).validate({
  errorElement: 'span',
    rules: {
      titlePostPublication: {
          required: true
      },
      messagePostPublication: {
          required: true
      }
    }
});

/*$( "#editPerson" ).validate({
  errorElement: 'div',
    rules: {
      firstName: {
          required: true
      },
      lastName: {
          required: true
      },
      mail: {
        required: true,
        email: true
      },
      website: {
        url: true
      },
      birthDate: {
        required: true,
        date: true
      },
      password: {
        required: true,
        minlength: 6,
        maxlength: 16
      },
      passwordConfirm: {
        required: true,
        equalTo: "#password"
      },
      groupe: {
        required: true
      }
    }
});

$( "#editGroupe" ).validate({
  errorElement: 'div',
    rules: {
      name: {
        required: true
      }
    }
});

$( "#loginForgotForm" ).validate({
	  errorElement: 'div',
	    rules: {
	      login: {
		    required: true
		  },
	      mail: {
	        required: true,
	        email: true
	      }
	    }
	});*/