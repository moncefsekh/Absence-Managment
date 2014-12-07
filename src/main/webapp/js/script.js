$(document).ready(function(){
	$('.btn').tooltip({
		placement: "bottom"
	});


   

    // $('.selectpicker').selectpicker('hide');

	//$('.dropdown-toggle').dropdown();
	
     /*$('#enseignants').dataTable({
                    "bPaginate": true,
                    "bLengthChange": false,
                    "bFilter": false,
                    "bSort": true,
                    "bInfo": true,
                    "bAutoWidth": false
     });*/

	/*
	$('#etudiants').on('click', function () {
		var li = $(this).parent("li");
		if (li.hasClass("active")){
			li.removeClass("active");
			$("#menu").slideUp(200).fadeOut(200);
		}else{
			$("#menu li").show();
			li.addClass("active");
			$("#menu").slideDown(350).fadeIn(200);
		}
	});

	$('#menu .row div ul li a').on('click', function () {
		$("#menu li").slideDown(250);
	});

	$('#menu .row div:last-child ul li a').on('click', function () {
		$("#menu li").not('.active').slideUp(200);
	});

	$('#liste-etudiants td').on('click',function() {
			var abs = "Absent",
				pre = "Présent";
			var etudiant = $(this).parent('tr').children('td:last-child')
			if(etudiant.find('span').hasClass('label-primary')){
				etudiant.find('span').removeClass("label-primary").addClass("label-danger").text(abs);
				$(this).parent('tr').addClass('danger-light');
				
			}else{
				etudiant.find('span').removeClass("label-danger").addClass("label-primary").text(pre);
				$(this).parent('tr').removeClass('danger-light');
				
			}
	});
	
*/
	/*
	$('#etudiants').on('click', function () {
		var li = $(this).parent("li");
		if (li.hasClass("active")){
			li.removeClass("active");
			$(".menu-etudiants").slideUp(200);
		}else{
			$(".menu-etudiants li").show();
			li.addClass("active");
			$(".menu-etudiants").slideDown(300);
		}
	});
	$('#etudiants').blur(function () {
		alert('blur');
		$('#etudiants').parent("li").removeClass('active');
	});
	*/
	$('ul.navbar-nav li a').on('click', function () {
		var li = $(this).parent("li");
		li.siblings().removeClass('active');
		li.addClass('active');
		// if(li.attr('id')=='etudiants'){
		// 	if($('.menu-etudiants').hasClass('ng-hide')){
		// 		console.log('ng-hide');
		// 		$(".menu-etudiants").removeClass('ng-hide').slideDown(300);

		// 	}else{
		// 		console.log('!ng-hide');
		// 		$(".menu-etudiants").addClass('ng-hide').slideUp(300);
		// 	}
		// }
		
	});

	$('.menu-groupes ul li a').on('click',function () {
		$('.menu-etudiants ul li').not('.active').slideUp(300);
	});

	// $("#liste-etudiants td").click(function () {
	// 		console.log('etudiabtclck');
	// 		var abs = "Absent",
	// 			pre = "Présent";
	// 		var etudiant = $(this).children('td:last-child')
	// 		if(etudiant.find('span').hasClass('label-primary')){
	// 			etudiant.find('span').removeClass("label-primary").addClass("label-danger").text(abs);
	// 			$(this).addClass('danger-light');
				
	// 		}else{
	// 			etudiant.find('span').removeClass("label-danger").addClass("label-primary").text(pre);
	// 			$(this).removeClass('danger-light');
				
	// 		}

	// 	});

	
	//console.log($(location).attr('hash'));
	$(".has-nav-child").each(function () {
		var btn = $(this).children("a").first();
        var menu = $(this).children(".nav-sidebar-child").first();
        var isActive = $(this).hasClass('active');

        if (isActive) {
            menu.show();
            btn.children(".glyphicon-chevron-left").first().removeClass("glyphicon-chevron-left").addClass("glyphicon-chevron-down");
        }

        btn.click(function(e) {
            e.preventDefault();
            if (isActive) {

                menu.slideUp();
                isActive = false;
                btn.children(".glyphicon-chevron-down").first().removeClass("glyphicon-chevron-down").addClass("glyphicon-chevron-left");
                btn.parent("li").removeClass("active");
            } else {

                menu.slideDown();
                isActive = true;
                btn.children(".glyphicon-chevron-left").first().removeClass("glyphicon-chevron-left").addClass("glyphicon-chevron-down");
                btn.parent("li").addClass("active");
            }
        });
    });

	// $('#type-barres').on('click', function () {
	// 	var container = $('#dx-chart').parent('div');
	// 	container.html('<div id="dx-chart" dx-chart="chartOptions" style="width:100%;height:300px;"></div>')
	// })
});