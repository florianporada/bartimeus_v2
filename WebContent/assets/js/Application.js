var app = angular.module("app", ["pascalprecht.translate"]).config(function($translateProvider) {
	$translateProvider.translations("en_US", {
		username: "Username",
		password: "Password",
		re_password: "Retype password",
		dashboard: "Dashboard",
		add: "Add",
		edit: "Edit",
		
		hash: "Unique ID",
		hash_description: "The Unique ID (UID) that is present on the backside of the sensor",
		name: "Name",
		name_description: "A name for the Sensor, to later easily identify it",
		
		about_description_title: "About Bartiméus",
		about_description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse laoreet quam sit amet diam convallis, ut mollis lacus scelerisque. Maecenas sit amet lorem vitae velit vulputate elementum eget vel odio. In ac bibendum ex. Nulla facilisi.",
		project_description_title: "About the project",
		project_description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse laoreet quam sit amet diam convallis, ut mollis lacus scelerisque. Maecenas sit amet lorem vitae velit vulputate elementum eget vel odio. In ac bibendum ex. Nulla facilisi.",
		app_description_title: "About the app",
		app_description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse laoreet quam sit amet diam convallis, ut mollis lacus scelerisque. Maecenas sit amet lorem vitae velit vulputate elementum eget vel odio. In ac bibendum ex. Nulla facilisi.",
		privacy_description_title: "Privacy policy",
		privacy_description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin eu rhoncus ex. Pellentesque felis sapien, varius quis velit nec, scelerisque pulvinar ex. Ut in urna vel metus lobortis consequat eu sed augue. Nunc sed malesuada dui. Mauris laoreet blandit porta. Ut cursus ornare purus ac auctor. Sed bibendum ac nibh at blandit. Maecenas nec tempus dui, id dictum eros. Quisque sagittis velit at bibendum mattis. Nunc finibus, leo accumsan pellentesque pulvinar, lacus orci rutrum elit, et fermentum velit erat ac mauris. Curabitur lobortis consequat rutrum. Curabitur sapien libero, viverra in libero sit amet, facilisis vehicula libero. Praesent ac tincidunt elit. Aliquam pulvinar augue ac pharetra gravida. Curabitur ante lorem, aliquet eget erat ut, semper bibendum velit. Vestibulum ac massa id justo pretium egestas non nec leo. Aliquam sodales odio eu nisi hendrerit, et vestibulum lorem ultricies. Praesent placerat nibh sem, ut vestibulum felis convallis luctus. Nulla iaculis ante vel efficitur lacinia. Suspendisse mollis orci sapien, et posuere quam consequat et. Nulla facilisi. Sed iaculis urna sit amet urna tincidunt lobortis. Aliquam risus ligula, imperdiet eu purus in, cursus rhoncus arcu. Praesent vehicula, massa id faucibus semper, neque enim tincidunt ipsum, sed varius leo enim sed nisl. Nunc sit amet consectetur ante. Mauris velit nulla, cursus eget tincidunt quis, tempus sed est. Nunc at purus efficitur, scelerisque felis id, gravida felis. Nunc vehicula vel felis placerat efficitur. Etiam ut ligula neque. Praesent vitae interdum purus. Etiam a dui nec orci hendrerit iaculis et sit amet nisl. In turpis mauris, sollicitudin id odio id, hendrerit ullamcorper ligula. Etiam sed luctus eros. Vivamus sit amet tellus nunc. Ut non laoreet tortor, eget tristique ante. Sed et leo sem. Ut aliquam dolor enim, a congue arcu cursus in. Nulla facilisi. Etiam interdum mauris eget risus pulvinar, et efficitur ante ultricies. Nulla nec massa accumsan, dictum sem sed, pharetra est. Sed efficitur vel nibh at maximus. Maecenas ut orci volutpat, vestibulum dui sed, finibus risus. Nullam enim libero, dictum sed tortor ut, efficitur ultrices eros. Mauris faucibus felis ut diam mattis aliquam. Donec eu commodo magna, fermentum hendrerit dui. Aliquam vitae leo ut ipsum varius malesuada. Nunc porta neque vestibulum lacus laoreet, et elementum libero convallis. Donec ultricies eros mollis lectus sollicitudin, molestie scelerisque sapien porta. Vivamus eget dolor id dui mattis mollis. Sed nec convallis ipsum. Aliquam at sagittis metus. Aenean pretium nisi vitae sapien tempor, id dignissim arcu efficitur.",
		terms_description_title: "Terms and conditions",
		terms_description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin eu rhoncus ex. Pellentesque felis sapien, varius quis velit nec, scelerisque pulvinar ex. Ut in urna vel metus lobortis consequat eu sed augue. Nunc sed malesuada dui. Mauris laoreet blandit porta. Ut cursus ornare purus ac auctor. Sed bibendum ac nibh at blandit. Maecenas nec tempus dui, id dictum eros. Quisque sagittis velit at bibendum mattis. Nunc finibus, leo accumsan pellentesque pulvinar, lacus orci rutrum elit, et fermentum velit erat ac mauris. Curabitur lobortis consequat rutrum. Curabitur sapien libero, viverra in libero sit amet, facilisis vehicula libero. Praesent ac tincidunt elit. Aliquam pulvinar augue ac pharetra gravida. Curabitur ante lorem, aliquet eget erat ut, semper bibendum velit. Vestibulum ac massa id justo pretium egestas non nec leo. Aliquam sodales odio eu nisi hendrerit, et vestibulum lorem ultricies. Praesent placerat nibh sem, ut vestibulum felis convallis luctus. Nulla iaculis ante vel efficitur lacinia. Suspendisse mollis orci sapien, et posuere quam consequat et. Nulla facilisi. Sed iaculis urna sit amet urna tincidunt lobortis. Aliquam risus ligula, imperdiet eu purus in, cursus rhoncus arcu. Praesent vehicula, massa id faucibus semper, neque enim tincidunt ipsum, sed varius leo enim sed nisl. Nunc sit amet consectetur ante. Mauris velit nulla, cursus eget tincidunt quis, tempus sed est. Nunc at purus efficitur, scelerisque felis id, gravida felis. Nunc vehicula vel felis placerat efficitur. Etiam ut ligula neque. Praesent vitae interdum purus. Etiam a dui nec orci hendrerit iaculis et sit amet nisl. In turpis mauris, sollicitudin id odio id, hendrerit ullamcorper ligula. Etiam sed luctus eros. Vivamus sit amet tellus nunc. Ut non laoreet tortor, eget tristique ante. Sed et leo sem. Ut aliquam dolor enim, a congue arcu cursus in. Nulla facilisi. Etiam interdum mauris eget risus pulvinar, et efficitur ante ultricies. Nulla nec massa accumsan, dictum sem sed, pharetra est. Sed efficitur vel nibh at maximus. Maecenas ut orci volutpat, vestibulum dui sed, finibus risus. Nullam enim libero, dictum sed tortor ut, efficitur ultrices eros. Mauris faucibus felis ut diam mattis aliquam. Donec eu commodo magna, fermentum hendrerit dui. Aliquam vitae leo ut ipsum varius malesuada. Nunc porta neque vestibulum lacus laoreet, et elementum libero convallis. Donec ultricies eros mollis lectus sollicitudin, molestie scelerisque sapien porta. Vivamus eget dolor id dui mattis mollis. Sed nec convallis ipsum. Aliquam at sagittis metus. Aenean pretium nisi vitae sapien tempor, id dignissim arcu efficitur.",
		
		incomming_notification: "Incomming notification",
		vibration_notification: "Vibration if notification",
		vibration_cont_notification: "Vibration if continued",
		
		logged_out: "You're logged out",
		click_logout: "Click here to logout",
		click_login: "Click here to login",
		login_error: "Invalid username or password",
		no_account: "Don't have an account?",
		create_account: "Create one now!",
		
		manage_app: "Manage Application",
		manage_sensor: "Manage Sensors",
		manage_not: "Manage Notifications",
		
		save_button: "Save",
		login_button: "Login",
		create_button: "Create",
		add_sensor: "Configure a sensor",
		new_sensor: "Add the sensor",
		edit_sensor: "Edit the sensor",
		
		create_title: "Create account",
		login_title: "Login",
		logout_title: "Logout",
		user_title: "User",
		home_title: "Home",
		about_title: "About",
		privacy_title: "Privacy",
		terms_title: "Terms",
	});
	
	$translateProvider.translations("nl_NL", {
		username: "Gebruikersnaam",
		password: "Wachtwoord",
		re_password: "Hertyp wachtwoord",
		add: "Toevoegen",
		edit: "Veranderen",
		
		hash: "Unieke ID",
		hash_description: "De Unieke ID (UID) die op de achterkant van de sensor staat",
		name: "Naam",
		name_description: "Een naam voor de Sensor, om later makkelijk terug te zien over welke Sensor het precies gaat",
		
		about_description_title: "Over Bartiméus",
		about_description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse laoreet quam sit amet diam convallis, ut mollis lacus scelerisque. Maecenas sit amet lorem vitae velit vulputate elementum eget vel odio. In ac bibendum ex. Nulla facilisi.",
		project_description_title: "Over het project",
		project_description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse laoreet quam sit amet diam convallis, ut mollis lacus scelerisque. Maecenas sit amet lorem vitae velit vulputate elementum eget vel odio. In ac bibendum ex. Nulla facilisi.",
		app_description_title: "Over de app",
		app_description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse laoreet quam sit amet diam convallis, ut mollis lacus scelerisque. Maecenas sit amet lorem vitae velit vulputate elementum eget vel odio. In ac bibendum ex. Nulla facilisi.",
		privacy_description_title: "Privacybeleid",
		privacy_description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin eu rhoncus ex. Pellentesque felis sapien, varius quis velit nec, scelerisque pulvinar ex. Ut in urna vel metus lobortis consequat eu sed augue. Nunc sed malesuada dui. Mauris laoreet blandit porta. Ut cursus ornare purus ac auctor. Sed bibendum ac nibh at blandit. Maecenas nec tempus dui, id dictum eros. Quisque sagittis velit at bibendum mattis. Nunc finibus, leo accumsan pellentesque pulvinar, lacus orci rutrum elit, et fermentum velit erat ac mauris. Curabitur lobortis consequat rutrum. Curabitur sapien libero, viverra in libero sit amet, facilisis vehicula libero. Praesent ac tincidunt elit. Aliquam pulvinar augue ac pharetra gravida. Curabitur ante lorem, aliquet eget erat ut, semper bibendum velit. Vestibulum ac massa id justo pretium egestas non nec leo. Aliquam sodales odio eu nisi hendrerit, et vestibulum lorem ultricies. Praesent placerat nibh sem, ut vestibulum felis convallis luctus. Nulla iaculis ante vel efficitur lacinia. Suspendisse mollis orci sapien, et posuere quam consequat et. Nulla facilisi. Sed iaculis urna sit amet urna tincidunt lobortis. Aliquam risus ligula, imperdiet eu purus in, cursus rhoncus arcu. Praesent vehicula, massa id faucibus semper, neque enim tincidunt ipsum, sed varius leo enim sed nisl. Nunc sit amet consectetur ante. Mauris velit nulla, cursus eget tincidunt quis, tempus sed est. Nunc at purus efficitur, scelerisque felis id, gravida felis. Nunc vehicula vel felis placerat efficitur. Etiam ut ligula neque. Praesent vitae interdum purus. Etiam a dui nec orci hendrerit iaculis et sit amet nisl. In turpis mauris, sollicitudin id odio id, hendrerit ullamcorper ligula. Etiam sed luctus eros. Vivamus sit amet tellus nunc. Ut non laoreet tortor, eget tristique ante. Sed et leo sem. Ut aliquam dolor enim, a congue arcu cursus in. Nulla facilisi. Etiam interdum mauris eget risus pulvinar, et efficitur ante ultricies. Nulla nec massa accumsan, dictum sem sed, pharetra est. Sed efficitur vel nibh at maximus. Maecenas ut orci volutpat, vestibulum dui sed, finibus risus. Nullam enim libero, dictum sed tortor ut, efficitur ultrices eros. Mauris faucibus felis ut diam mattis aliquam. Donec eu commodo magna, fermentum hendrerit dui. Aliquam vitae leo ut ipsum varius malesuada. Nunc porta neque vestibulum lacus laoreet, et elementum libero convallis. Donec ultricies eros mollis lectus sollicitudin, molestie scelerisque sapien porta. Vivamus eget dolor id dui mattis mollis. Sed nec convallis ipsum. Aliquam at sagittis metus. Aenean pretium nisi vitae sapien tempor, id dignissim arcu efficitur.",
		terms_description_title: "Voorwaarden",
		terms_description: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin eu rhoncus ex. Pellentesque felis sapien, varius quis velit nec, scelerisque pulvinar ex. Ut in urna vel metus lobortis consequat eu sed augue. Nunc sed malesuada dui. Mauris laoreet blandit porta. Ut cursus ornare purus ac auctor. Sed bibendum ac nibh at blandit. Maecenas nec tempus dui, id dictum eros. Quisque sagittis velit at bibendum mattis. Nunc finibus, leo accumsan pellentesque pulvinar, lacus orci rutrum elit, et fermentum velit erat ac mauris. Curabitur lobortis consequat rutrum. Curabitur sapien libero, viverra in libero sit amet, facilisis vehicula libero. Praesent ac tincidunt elit. Aliquam pulvinar augue ac pharetra gravida. Curabitur ante lorem, aliquet eget erat ut, semper bibendum velit. Vestibulum ac massa id justo pretium egestas non nec leo. Aliquam sodales odio eu nisi hendrerit, et vestibulum lorem ultricies. Praesent placerat nibh sem, ut vestibulum felis convallis luctus. Nulla iaculis ante vel efficitur lacinia. Suspendisse mollis orci sapien, et posuere quam consequat et. Nulla facilisi. Sed iaculis urna sit amet urna tincidunt lobortis. Aliquam risus ligula, imperdiet eu purus in, cursus rhoncus arcu. Praesent vehicula, massa id faucibus semper, neque enim tincidunt ipsum, sed varius leo enim sed nisl. Nunc sit amet consectetur ante. Mauris velit nulla, cursus eget tincidunt quis, tempus sed est. Nunc at purus efficitur, scelerisque felis id, gravida felis. Nunc vehicula vel felis placerat efficitur. Etiam ut ligula neque. Praesent vitae interdum purus. Etiam a dui nec orci hendrerit iaculis et sit amet nisl. In turpis mauris, sollicitudin id odio id, hendrerit ullamcorper ligula. Etiam sed luctus eros. Vivamus sit amet tellus nunc. Ut non laoreet tortor, eget tristique ante. Sed et leo sem. Ut aliquam dolor enim, a congue arcu cursus in. Nulla facilisi. Etiam interdum mauris eget risus pulvinar, et efficitur ante ultricies. Nulla nec massa accumsan, dictum sem sed, pharetra est. Sed efficitur vel nibh at maximus. Maecenas ut orci volutpat, vestibulum dui sed, finibus risus. Nullam enim libero, dictum sed tortor ut, efficitur ultrices eros. Mauris faucibus felis ut diam mattis aliquam. Donec eu commodo magna, fermentum hendrerit dui. Aliquam vitae leo ut ipsum varius malesuada. Nunc porta neque vestibulum lacus laoreet, et elementum libero convallis. Donec ultricies eros mollis lectus sollicitudin, molestie scelerisque sapien porta. Vivamus eget dolor id dui mattis mollis. Sed nec convallis ipsum. Aliquam at sagittis metus. Aenean pretium nisi vitae sapien tempor, id dignissim arcu efficitur.",
		
		incomming_notification: "Inkomende notificatie",
		vibration_notification: "Vibratie bij notificatie",
		vibration_cont_notification: "Vibratie bij vervolg",
		
		dashboard: "Dashboard",
		logged_out: "U bent uitgelogd",
		click_logout: "Klik hier om uit te loggen",
		click_login: "Klik hier om in te loggen",
		login_error: "Ongeldioge gebruikersnaam of wachtwoord",
		no_account: "Heeft u nog geen account?",
		create_account: "Maak er dan nu één aan!",
		
		manage_app: "Beheer Applicaties",
		manage_sensor: "Beheer Sensoren",
		manage_not: "Beheer Notificaties",
		
		save_button: "Opslaan",
		login_button: "Inloggen",
		create_button: "Creëer uw account",
		add_sensor: "Configureer een sensor",
		new_sensor: "Voeg de nieuwe sensor toe",
		edit_sensor: "Verander de sensor",
		
		create_title: "Creëer een account",
		login_title: "Inloggen",
		logout_title: "Uitloggen",
		user_title: "Gebruiker",
		home_title: "Home",
		about_title: "Over",
		privacy_title: "Privacy",
		terms_title: "Voorwaarden",
	});
	
    var language = window.localStorage.getItem("language") || "nl_NL";
    $translateProvider.use(language);
});

app.run(function($rootScope) {
	var language = window.localStorage.getItem("language") || "nl_NL";
	$rootScope.LANG = language;
});