
/*----------------------------------------------
	Script Name : Responsive Mailform
	Author : FIRSTSTEP - Motohiro Tani
	Author URL : https://www.1-firststep.com/
	Create Date : 2014/3/25
	Version : 3.0
	Last Update : 2016/10/21
----------------------------------------------*/



	
	var mailform_dt = $('#mail_form dl dt');
	var start_time = 0;
	var stop_time = 0;
	var writing_time = 0;
	
	
	for(var i=0; i<mailform_dt.length-1; i++){
		if( mailform_dt.eq(i).next('dd').attr('class') == 'required' ){
			$('<span/>')
				.text('必填')
				.addClass('required')
				.prependTo($(mailform_dt.eq(i)));
			
			$('<span/>')
				.appendTo(mailform_dt.eq(i).next('dd'));
		}else{
			$('<span/>')
				.text('任意')
				.addClass('optional')
				.prependTo($(mailform_dt.eq(i)));
		}
	}
	
	
	
	
	$('input').on('keydown', function(e){
		if( (e.which && e.which === 13) || (e.keyCode && e.keyCode === 13) ){
			return false;
		}else{
			return true;
		}
	});
	
	
	
	
	$('.required').children('textarea#mail_contents').focus(function(){
		start_time = new Date();
	});
	
	$('.required').children('textarea#mail_contents').blur(function(){
		stop_time = new Date();
		writing_time += Math.round( ( stop_time - start_time ) / 1000 );
	});
	
	
	function slice_method(dt){
		var span_start = dt.html().indexOf('</span>');
		var span_end = dt.html().lastIndexOf('<span');
		var dt_name = dt.html().slice(span_start+7, span_end);
		return dt_name;
	}
	
	
	
	
	function compare_method(s, e){
		if( s>e ){
			return e;
		}else{
			return s;
		}
	}
	
	
	
	
	function required_check(){
		
		var error = 0;
		var scroll_point = $('body').height();
		
		
		if( $('form#mail_form dd.required').length ){
			
			if( $('.required').children('input#name_1').length ){
				var element = $('.required').children('input#name_1');
				if( element.val() == ''){
					var dt = element.parents('dd').prev('dt');
					var dt_name = slice_method(dt);
					element.nextAll('span').text("请输入"+dt_name);
					error++;
					scroll_point = compare_method(scroll_point, element.offset().top);
				}else{
					element.nextAll('span').text('');
				}
			}

			if( $('.required').children('input#phone').length ){
				var element = $('.required').children('input#phone');
				if( element.val() == '' ){
					var dt = element.parents('dd').prev('dt');
					var dt_name = slice_method(dt);
					element.nextAll('span').text("请输入"+dt_name);
					error++;
					scroll_point = compare_method(scroll_point, element.offset().top);
				}else{
					element.nextAll('span').text('');
				}
			}

			if( $('.required').children('input#vehicle_brand').length ){
				var element = $('.required').children('input#vehicle_brand');
				if( element.val() == '' ){
					var dt = element.parents('dd').prev('dt');
					var dt_name = slice_method(dt);
					element.nextAll('span').text("请输入"+dt_name);
					error++;
					scroll_point = compare_method(scroll_point, element.offset().top);
				}else{
					element.nextAll('span').text('');
				}
			}

			if( $('.required').children('input#mail_address').length ){
				var element = $('.required').children('input#mail_address');
				if( element.val() == '' ){
					var dt = element.parents('dd').prev('dt');
					var dt_name = slice_method(dt);
					element.nextAll('span').text("请输入"+dt_name);
					error++;
					scroll_point = compare_method(scroll_point, element.offset().top);
				}else{
					element.nextAll('span').text('');
				}
			}
			
			if( $('.required').children('input#model').length ){
				var element = $('.required').children('input#model');
				if( element.val() == '' ){
					var dt = element.parents('dd').prev('dt');
					var dt_name = slice_method(dt);
					element.nextAll('span').text("请输入"+dt_name);
					error++;
					scroll_point = compare_method(scroll_point, element.offset().top);
				}else{
					element.nextAll('span').text('');
				}
			}

			
			
			if( $('.required').children('textarea#content').length ){
				var element = $('.required').children('textarea#content');
				if( element.val() == '' ){
					var dt = element.parents('dd').prev('dt');
					var dt_name = slice_method(dt);
					element.nextAll('span').text("请输入"+dt_name);
					error++;
					scroll_point = compare_method(scroll_point, element.offset().top);
				}else{
					element.nextAll('span').text('');
				}
			}
			
		}
		
		
		if(error == 0){
			
		}else{
			$('html,body').animate({
				scrollTop : scroll_point-50
			}, 500);
			return false;
		}
	
	}
	




$('#mail_submit_button').click(function(){
	var check = required_check();
	if(undefined != check){
		return false;
	}
	var datas = $('#mail_form').serializeArray();
	$.ajax({
		url : '/contact/save',
		type : "post",
		data:datas,
		cache:false,
		dataType : "json",
		success : function(data) {
			alert(data.msg)
			if(data.code == 200){
				$('form')[0].reset();
			}
		},
		error : function(err) {
			console.log(err)
		}
	});
	
	
});
