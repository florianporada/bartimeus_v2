var app = angular.module("app", ["pascalprecht.translate", "monospaced.qrcode"]).config(function($translateProvider) {
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
		privacy_description: "<h2>1.0 What personal information do we collect?</h2><p>We do not collect any kind of information or other details from visitors on the free-to-visit parts on www.theassistant.io</p><h3>1.1 When do we collect information?</h3><p>When the visitor registers for an account to make use of the web-app connected to one ofour products we will collect the login credentials, e-mail address and name of the visitor.</p><h3>1.2 How do we use your information?</h3><p>We may use the information we collect from you when you register or use certain other site features in the following ways:</p><ul><li>To allow us to provide better service on you responding to your customer service requests</li><li>To follow up with them after correspondence </li><li>To grant you access to your personal product-environment</li></ul><h3>1.3 How do we protect your information?</h3><p>Our servers and databases are being protected using up to date security technologies using:</p><ul><li>We use vulnerability scanning.</li><li>We never ask for credit card of social security numbers.</li><li>We user regular Malware scanning.</li></ul><h2>2.0 Cookies</h2><p>We currently do not, but may in the future, use cookies for tracking purposes. The uses of cookies will be to improve the user-experience and will not be sold to third-parties, see point 3 of this Privacy Policy. You can choose to have your computer warn you each time a cookie is being sent, or you can choose to turn off all cookies. You do this through your browser settings. Since every browser is different see your browsers’ Help Menu.</p><h2>3.0 Third-party disclosure</h2><p>We do not sell, trade, or otherwise transfer your Personally Identifiable Information unless we provide users with advance notice. This does not include webhosting and other parties who assist us in operating our website, conducting our business, or serving our users, so long the parties agree to keep this information confidential. We may also release information when it’s release is appropriate to comply with the law, enforce our site policies, or protect ours or others’ rights, property or safety.</p><h3>3.1 Third party links</h3><p>We do not include or offer third-party products or services on our website.</p> <h3>3.2 Google</h3><p>Google’s advertising requirements can be summed up by Google’s Advertising Principles. They are put in place to provide a positive experience for users. <a href='https://support.google.com/adwordspolicy/answer/1316548?hl=en'>https://support.google.com/adwordspolicy/answer/1316548?hl=en</a>We have not enabled Google AdSense on our site but we may do so in the future.</p> <h2>4.0 Fair Information Practices (FIP), responsive actions should a data breach occur</h2><h3>4.1 Via email</h3><p>In case a data breach occurs we will notify you via email within 7 business days</p><h3>4.2 Via in-site notification</h3><p>In case a data breach occurs we will notify you via in-site notification within 7 business days</p><h2>5.0 Contacting us</h2><p>If there are any questions about this Privacy Policy or our product, you may contact us using the information below.</p> <p><a href='https://www.theassistant.io'>https://www.theassistant.io</a></p><p>Project team Bartiméus (Project Internet of Things)</p><p>Domein Media Creatie en Informatie</p><p>Amsterdam University</p><p>Amsterdam, The Netherlands</p>",
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
		privacy_description: "<h2>1.0 Welke persoonlijke informatie verzamelen we?</h2><p>We verzamelen geen enkele vorm van informatie op de vrij toegankelijke onderdelen op www.theassistant.io</p><h3>1.1 Wanneer verzamelen we informatie?</h3><p>Wanneer de gebruiker registreert voor een account om gebruik te maken van de webapplicatie gekoppeld aan één van onze producten verzamelen we de inlog gegevens, het e-mail adres en de naam van de gebruiker.</p> <h3>1.2 Hoe gebruiken we deze informatie?</h3><p>We gebruiken deinformatie die we verzamelen wanneer je registreert of van andere website features gebruik maakt op de volgende manier:</p><ul><li>Om je een betere service te bieden wanneer je gebruik maakt van service aanvragen</li><li>Om je contact met je op te nemen wanneer je een service aanvraag achterlaat</li><li>Om je toegang te geven tot je persoonlijk product omgeving</li></ul><h3>1.3 Hoe beschermen we de informatie?</h3><p>Onze servers en databases zijn beschermd door moderne technologieën die gebruik maken van:</p><ul><li> We gebruiken vulnerability scanning</li><li>We vragen nooitom bankgegevens of burgerservicenummers</li><li>We gebruiken regelmatig Malware scans</li></ul><h2>2.0 Cookies</h2><p>We gebruiken geen, maar de mogelijkheid bestaat dat het gebruik gaat worden, cookies om je gedrag op onze website te volgen. Het gebruik van cookies zal zijn om de gebruikerservaring van onze website te verbeteren en zal niet aan derden worden doorverkocht, zie punt drie van deze privacy policy. Je kan ervoor kiezen je computer je elke keer te laten waarschuwen wanneer er een cookie gestuurd word, je kan er ook voor kiezen alle cookies uit te schakelen. Dit kan via de instellingen van je internet browser. Aangezien elke browser verschillend is verwijzen we je hiervoor door naar je browsers Help Menu. </p><h2>3.0 Openbaarmaking derden</h2><p>We verkopen, ruilen, of delen op een andere manier geen Persoonlijk Identificeerbare Informatie, wanneer wij dit wel doen wordt u hier vooraf over ingelicht.</p> <h3>3.1 Links derden</h3><p>We verkopen of bieden geen producten of services van derden aan op onze website.</p> <h3>3.2 Google</h3><p>Google zijn advertentie vereisten worden opgesomd door Googles Advertentie Principes. Deze zijn geplaatst voor een positieve gebruikerservaring<a href='https://support.google.com/adwordspolicy/answer/1316548?hl=en'>https://support.google.com/adwordspolicy/answer/1316548?hl=en</a>We gebruiken geen Google adsense, maar zouden dit in de toekomst wel kunnen doen.</p><h2>4.0 Wet Persoons-en gegevensbeveiligingin geval van data-lekken</h2><h3>4.1 Via E-mail</h3><p>In het geval van data-lekken zullen wij binnen 7 dagen via de mail contact met u opnemen.</p><h3>4.2 Via website-notificaties</h3><p>In het geval van data-lekken zullen wij binnen 7 dagen een notificatie op de website plaatsen.</p><h2>5.0 Contactgegevens</h2><p>Indien er vragen zijn over de Privacy Policy of ons product mag u contact opnemen via de volgende informatie:</p><p><a href='https://www.theassistant.io'>https://www.theassistant.io</a></p><p>Project team Bartiméus (Project Internet of Things)</p><p>Domein Media Creatie en Informatie</p><p>Hogeschool van Amsterdam</p><p>Amsterdam, Nederland</p>",
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