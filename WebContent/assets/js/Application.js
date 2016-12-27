var app = angular.module("app", ["pascalprecht.translate"]).config(function($translateProvider) {
	$translateProvider.translations("en_US", {
		username: "Username",
		password: "Password",
		re_password: "Retype password",
		logged_out: "You're logged out",
		click_logout: "Click here to logout",
		click_login: "Click here to login",
		login_error: "Invalid username or password",
		no_account: "Don't have an account?",
		create_account: "Create one now!",
		
		manage_app: "Manage Application",
		manage_sensor: "Manage Sensors",
		manage_not: "Manage Notifications",
		
		login_button: "Login",
		create_button: "Create",
		
		create_title: "Create account",
		login_title: "Login",
		logout_title: "Logout",
		user_title: "User",
		home_title: "Home",
	});
	
	$translateProvider.translations("nl_NL", {
		username: "Gebruikersnaam",
		password: "Wachtwoord",
		re_password: "Hertyp wachtwoord",
		logged_out: "U bent uitgelogd",
		click_logout: "Klik hier om uit te loggen",
		click_login: "Klik hier om in te loggen",
		login_error: "Ongeldioge gebruikersnaam of wachtwoord",
		no_account: "Heeft u nog geen account?",
		create_account: "Maak er dan nu één aan!",
		
		manage_app: "Beheer Applicaties",
		manage_sensor: "Beheer Sensoren",
		manage_not: "Beheer Notificaties",
		
		login_button: "Inloggen",
		create_button: "Creëer uw account",
		
		create_title: "Creëer een account",
		login_title: "Inloggen",
		logout_title: "Uitloggen",
		user_title: "Gebruiker",
		home_title: "Home",
	});
	
    var language = window.localStorage.getItem("language") || "nl_NL";
    $translateProvider.use(language);
});

app.run(function($rootScope) {
	var language = window.localStorage.getItem("language") || "nl_NL";
	$rootScope.LANG = language;
});