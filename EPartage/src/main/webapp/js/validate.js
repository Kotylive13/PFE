$("#updateInformationForm").validate({
	errorElement : 'span',
	rules : {
		numStudent : {
			required : true,
			maxlength : 10
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
			required : true,
			minlength: 6,
			maxlength: 32
		},
		adress : {
			required : true
		},
		promo : {
			required : true
		},
		phone : {
			required : true,
			minlength: 10,
			maxlength: 10
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
			required : true,
			maxlength: 32
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
			required : true,
			maxlength: 32
		},
		'idCategory.group' : {
			required : true,
			maxlength: 32
		}
	}
});

$("#addSubcategory").validate({
	errorElement : 'span',
	rules : {
		'idSubcategory.subcategory' : {
			required : true,
			maxlength: 32
		}
	}
});

$("#modifyGroup").validate({
	errorElement : 'span',
	rules : {
		name : {
			required : true,
			maxlength: 32
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
			required : true,
			minlength: 2,
			maxlength: 2048
		}
	}
});

$("#postPublicationForm").validate({
	errorElement : 'span',
	rules : {
		titlePostPublication : {
			required : true,
			maxlength: 32
		},
		messagePostPublication : {
			required : true
		}
	}
});

$("#contactFormUser").validate({
	errorElement : 'span',
	rules : {
		object : {
			required : true
		},
		message : {
			required : true
		}
	}
});

$("#contactFormVisitor").validate({
	errorElement : 'span',
	rules : {
		lastName : {
			required : true
		},
		firstName : {
			required : true
		},
		email : {
			required : true,
			email: true
		},
		object : {
			required : true
		},
		message : {
			required : true,
			minlength: 2,
			maxlength: 2048
		}
	}
});