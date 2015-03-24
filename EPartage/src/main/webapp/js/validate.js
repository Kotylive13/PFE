$("#updateInformationForm").validate({
	errorElement : 'span',
	rules : {
		numStudent : {
			required : true
		},
		inscriptUnivDate : {
			required : true
		},
		lastName : {
			required : true
		},
		firstName : {
			required : true
		},
		birthDate : {
			required : true
		},
		email : {
			required : true,
			email: true
		},
		password : {
			required : true
		},
		confirmation : {/*
			required: true,
	        equalTo: "#password"*/
		},
		adress : {
			required : true
		},
		promo : {
			required : true
		},
		phone : {
			required : true
		},
		tokenizer : {
			required : true
		}
	}
});

$("#addGroup").validate({
	errorElement : 'span',
	rules : {
		name : {
			required : true
		},
		description : {
			required : true
		}
	}
});

$("#addCategory").validate({
	errorElement : 'span',
	rules : {
		'idCategory.name' : {
			required : true
		},
		'idCategory.group' : {
			required : true
		}
	}
});

$("#addSubcategory").validate({
	errorElement : 'span',
	rules : {
		'idSubcategory.subcategory' : {
			required : true
		}
	}
});

$("#modifyGroup").validate({
	errorElement : 'span',
	rules : {
		name : {
			required : true
		},
		description : {
			required : true
		}
	}
});

$("#newMessage").validate({
	errorElement : 'span',
	rules : {
		content : {
			required : true
		}
	}
});

$("#postPublicationForm").validate({
	errorElement : 'span',
	rules : {
		titlePostPublication : {
			required : true
		},
		messagePostPublication : {
			required : true
		}
	}
});

/*
 * $( "#editPerson" ).validate({ errorElement: 'div', rules: { firstName: {
 * required: true }, lastName: { required: true }, mail: { required: true,
 * email: true }, website: { url: true }, birthDate: { required: true, date:
 * true }, password: { required: true, minlength: 6, maxlength: 16 },
 * passwordConfirm: { required: true, equalTo: "#password" }, groupe: {
 * required: true } } });
 *  $( "#editGroupe" ).validate({ errorElement: 'div', rules: { name: {
 * required: true } } });
 *  $( "#loginForgotForm" ).validate({ errorElement: 'div', rules: { login: {
 * required: true }, mail: { required: true, email: true } } });
 */