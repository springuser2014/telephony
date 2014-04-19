<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<c:if test="${environment == 'PROD'}">
	<jsp:include page="include/header_css_prod.jsp" />
	<jsp:include page="include/header_js_prod.jsp" />
</c:if>

<c:if test="${environment == 'TEST'}">
	<jsp:include page="include/header_css_test.jsp" />
	<jsp:include page="include/header_js_test.jsp" />
</c:if>
</head>

<body>
    
    <jsp:include page="top.jsp"/>

    <div class="container theme-showcase">
    <div class="row show-grid">
	    <div class="col-md-4">
	        
	    </div>
	    <div class="col-md-4">                        
	                        
		<div class="jarviswidget jarviswidget-sortable" id="wid-id-4"
			data-widget-editbutton="false" data-widget-custombutton="false"
			role="widget" style="">
			<!-- widget options:
	                    usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">
	                    
	                    data-widget-colorbutton="false" 
	                    data-widget-editbutton="false"
	                    data-widget-togglebutton="false"
	                    data-widget-deletebutton="false"
	                    data-widget-fullscreenbutton="false"
	                    data-widget-custombutton="false"
	                    data-widget-collapsed="true" 
	                    data-widget-sortable="false"
	                    
	                -->
			<header role="heading">
				<div class="jarviswidget-ctrls" role="menu">
					<a href="#" class="button-icon jarviswidget-toggle-btn"
						rel="tooltip" title="" data-placement="bottom"
						data-original-title="Collapse"><i class="fa fa-minus "></i></a> <a
						href="javascript:void(0);"
						class="button-icon jarviswidget-fullscreen-btn" rel="tooltip"
						title="" data-placement="bottom" data-original-title="Fullscreen"><i
						class="fa fa-resize-full "></i></a> <a href="javascript:void(0);"
						class="button-icon jarviswidget-delete-btn" rel="tooltip" title=""
						data-placement="bottom" data-original-title="Delete"><i
						class="fa fa-times"></i></a>
				</div>
				<div class="widget-toolbar" role="menu">
					<a data-toggle="dropdown" class="dropdown-toggle color-box selector"
						href="javascript:void(0);"></a>
					<ul class="dropdown-menu arrow-box-up-right color-select pull-right">
						<li><span class="bg-color-green"
							data-widget-setstyle="jarviswidget-color-green" rel="tooltip"
							data-placement="left" data-original-title="Green Grass"></span></li>
						<li><span class="bg-color-greenDark"
							data-widget-setstyle="jarviswidget-color-greenDark" rel="tooltip"
							data-placement="top" data-original-title="Dark Green"></span></li>
						<li><span class="bg-color-greenLight"
							data-widget-setstyle="jarviswidget-color-greenLight" rel="tooltip"
							data-placement="top" data-original-title="Light Green"></span></li>
						<li><span class="bg-color-purple"
							data-widget-setstyle="jarviswidget-color-purple" rel="tooltip"
							data-placement="top" data-original-title="Purple"></span></li>
						<li><span class="bg-color-magenta"
							data-widget-setstyle="jarviswidget-color-magenta" rel="tooltip"
							data-placement="top" data-original-title="Magenta"></span></li>
						<li><span class="bg-color-pink"
							data-widget-setstyle="jarviswidget-color-pink" rel="tooltip"
							data-placement="right" data-original-title="Pink"></span></li>
						<li><span class="bg-color-pinkDark"
							data-widget-setstyle="jarviswidget-color-pinkDark" rel="tooltip"
							data-placement="left" data-original-title="Fade Pink"></span></li>
						<li><span class="bg-color-blueLight"
							data-widget-setstyle="jarviswidget-color-blueLight" rel="tooltip"
							data-placement="top" data-original-title="Light Blue"></span></li>
						<li><span class="bg-color-teal"
							data-widget-setstyle="jarviswidget-color-teal" rel="tooltip"
							data-placement="top" data-original-title="Teal"></span></li>
						<li><span class="bg-color-blue"
							data-widget-setstyle="jarviswidget-color-blue" rel="tooltip"
							data-placement="top" data-original-title="Ocean Blue"></span></li>
						<li><span class="bg-color-blueDark"
							data-widget-setstyle="jarviswidget-color-blueDark" rel="tooltip"
							data-placement="top" data-original-title="Night Sky"></span></li>
						<li><span class="bg-color-darken"
							data-widget-setstyle="jarviswidget-color-darken" rel="tooltip"
							data-placement="right" data-original-title="Night"></span></li>
						<li><span class="bg-color-yellow"
							data-widget-setstyle="jarviswidget-color-yellow" rel="tooltip"
							data-placement="left" data-original-title="Day Light"></span></li>
						<li><span class="bg-color-orange"
							data-widget-setstyle="jarviswidget-color-orange" rel="tooltip"
							data-placement="bottom" data-original-title="Orange"></span></li>
						<li><span class="bg-color-orangeDark"
							data-widget-setstyle="jarviswidget-color-orangeDark" rel="tooltip"
							data-placement="bottom" data-original-title="Dark Orange"></span></li>
						<li><span class="bg-color-red"
							data-widget-setstyle="jarviswidget-color-red" rel="tooltip"
							data-placement="bottom" data-original-title="Red Rose"></span></li>
						<li><span class="bg-color-redLight"
							data-widget-setstyle="jarviswidget-color-redLight" rel="tooltip"
							data-placement="bottom" data-original-title="Light Red"></span></li>
						<li><span class="bg-color-white"
							data-widget-setstyle="jarviswidget-color-white" rel="tooltip"
							data-placement="right" data-original-title="Purity"></span></li>
						<li><a href="javascript:void(0);"
							class="jarviswidget-remove-colors" data-widget-setstyle=""
							rel="tooltip" data-placement="bottom"
							data-original-title="Reset widget color to default">Remove</a></li>
					</ul>
				</div>
				<span class="widget-icon"> <i class="fa fa-edit"></i>
				</span>
				<h2>Registration form</h2>
	
				<span class="jarviswidget-loader"><i
					class="fa fa-refresh fa-spin"></i></span>
			</header>
	
			<!-- widget div-->
			<div role="content">
	
				<!-- widget edit box -->
				<div class="jarviswidget-editbox">
					<!-- This area used as dropdown edit box -->
	
				</div>
				<!-- end widget edit box -->
	
				<!-- widget content -->
				<div class="widget-body no-padding">
	
					<form id="smart-form-register" class="smart-form"
						novalidate="novalidate">
						<header> Registration form </header>
	
						<fieldset>
							<section>
								<label class="input"> <i class="icon-append fa fa-user"></i>
									<input type="text" name="username" placeholder="Username">
									<b class="tooltip tooltip-bottom-right">Needed to enter the
										website</b>
								</label>
							</section>
	
	
	
							<section>
								<label class="input"> <i
									class="icon-append fa fa-envelope-o"></i> <input type="email"
									name="email" placeholder="Email address"> <b
									class="tooltip tooltip-bottom-right">Needed to verify your
										account</b>
								</label>
							</section>
	
							<section>
								<label class="input"> <i class="icon-append fa fa-lock"></i>
									<input type="password" name="password" placeholder="Password"
									id="password"> <b class="tooltip tooltip-bottom-right">Don't
										forget your password</b>
								</label>
							</section>
	
							<section>
								<label class="input"> <i class="icon-append fa fa-lock"></i>
									<input type="password" name="passwordConfirm"
									placeholder="Confirm password"> <b
									class="tooltip tooltip-bottom-right">Don't forget your
										password</b>
								</label>
							</section>
						</fieldset>
	
						<fieldset>
							<div class="row">
								<section class="col col-6">
									<label class="input"> <input type="text"
										name="firstname" placeholder="First name">
									</label>
								</section>
								<section class="col col-6">
									<label class="input"> <input type="text" name="lastname"
										placeholder="Last name">
									</label>
								</section>
							</div>
	
							<div class="row">
								<section class="col col-6">
									<label class="select"> <select name="gender">
											<option value="0" selected="" disabled="">Gender</option>
											<option value="1">Male</option>
											<option value="2">Female</option>
											<option value="3">Prefer not to answer</option>
									</select> <i></i>
									</label>
								</section>
								<section class="col col-6">
									<label class="input"> <i
										class="icon-append fa fa-calendar"></i> 
										
										<input type="text"
										name="request" placeholder="Request activation on"
										class="datepicker" data-dateformat="dd/mm/yy"
										id="asdasd">
									</label>
								</section>
							</div>
	
							<section>
								<label class="checkbox"> <input type="checkbox"
									name="subscription" id="subscription"> <i></i>I want to
									receive news and special offers
								</label> <label class="checkbox"> <input type="checkbox"
									name="terms" id="terms"> <i></i>I agree with the Terms
									and Conditions
								</label>
							</section>
						</fieldset>
						<footer>
							<button type="submit" class="btn btn-primary">Validate
								Form</button>
						</footer>
					</form>
	
				</div>
				<!-- end widget content -->
	
			</div>
			<!-- end widget div -->
	
		</div>
		
		
	      </div>
	      <div class="col-md-4">
	      
	      </div>
    </div>    
    </div>
    
    
</body>
</html>