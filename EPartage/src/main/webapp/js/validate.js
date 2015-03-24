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
			required : true
		},
		object : {
			required : true
		},
		message : {
			required : true
		}
	}
});