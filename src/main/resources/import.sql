
-- USUARIOS

INSERT INTO public.users (id,date_created,date_last_modified,email,email_verified,first_name,last_name,"password","role") VALUES
	 ('40cc3ba9-16bd-42ef-a10f-14614da4a267','2023-11-15 20:13:27.033','2023-11-15 20:13:27.033','usuario1@example.com',false,'Juan','Pérez','$2a$10$xe/9LuLpNlX5SlHarLXll.483gwmrfluDsPcaaACU27BK7DTfzxZq','ROLE_USER'),
	 ('e795d9a5-5aba-438f-8ab4-22bd6b4e28bf','2023-11-15 20:13:37.257','2023-11-15 20:13:37.257','usuario2@example.com',false,'María','González','$2a$10$5IqatLyuD5Q8apdQ7B7IlepUOYxAlHsZxjzcTOjkeMDIPaReekoGy','ROLE_USER'),
	 ('0fdd00a1-1983-435f-a95e-578531b7021c','2023-11-15 20:13:53.672','2023-11-15 20:13:53.672','usuario3@example.com',false,'Carlos','López','$2a$10$WfE1ixSpOjVoSX7HUAcazuNBho489HF4Dh7meiU2nD6YN3iofJh/6','ROLE_USER'),
	 ('ddff37cd-d6bc-4242-ab78-f332326cfa2b','2023-11-15 20:14:03.158','2023-11-15 20:14:03.158','usuario4@example.com',false,'Laura','Martínez','$2a$10$5bEaJwWsCrJ0r/rYrAr6QOs891mpMpDMJ/441cFHyum8Q.BP0.4qm','ROLE_USER'),
	 ('f57fa566-cb49-46d6-aa4f-e66f6f70e863','2023-11-15 20:14:11.089','2023-11-15 20:14:11.089','usuario5@example.com',false,'Pedro','Sánchez','$2a$10$MVCb3ovnxSWcpK1Wpp87L.2xdr/sgfdA02YvKP1f7axxiEnnppGM2','ROLE_USER'),
	 ('192f1f02-68d1-4d80-96ee-fe51b66a4cd3','2023-11-15 20:14:18.766','2023-11-15 20:14:18.766','usuario6@example.com',false,'Ana','Ramírez','$2a$10$wVHullrKrV0X2UnyRStBgeQ2eK9K/ro.3HXxCdnT8hhvSDBtZs8R2','ROLE_USER'),
	 ('0d16db0b-9f9b-4737-8314-916a45c3d859','2023-11-15 20:14:28.011','2023-11-15 20:14:28.011','usuario7@example.com',false,'David','Hernández','$2a$10$FaU8QMjtXfYNYeATn0weFu1isRPli1v0TPGR2Oj6brFGHWL5CU3mG','ROLE_USER'),
	 ('6ea43e30-ebe9-47fb-828a-12f29cf0fce3','2023-11-15 20:14:35.287','2023-11-15 20:14:35.287','usuario8@example.com',false,'Sofía','Torres','$2a$10$gnS4NCNve113skbyMbpd3eDrSV7VXapmVEseL3dCxiqk3P1L2SLBq','ROLE_USER'),
	 ('5f5a5852-30a4-449e-9900-9bfc98956767','2023-11-15 20:14:42.934','2023-11-15 20:14:42.934','usuario9@example.com',false,'Javier','Díaz','$2a$10$VQSVESDafeID6gmC8GGVjOfwQCvcKNTQ3JWLwPDE8elllkJM7STZ2','ROLE_USER'),
	 ('c97e435e-a882-4254-abb6-b04e141704a8','2023-11-15 20:14:53.591','2023-11-15 20:14:53.591','usuario10@example.com',false,'Elena','Ruiz','$2a$10$vGCn0T70PiplPOHK.eeC3OvhrOSQ2L8pZiSrfwIPKKW3H4snDlShi','ROLE_USER');
INSERT INTO public.users (id,date_created,date_last_modified,email,email_verified,first_name,last_name,"password","role") VALUES
	 ('07ea76e0-142f-499a-929f-bedb08c79624','2023-11-15 19:56:01.684','2023-11-15 19:56:01.684','ricardohuaripatabellido@gmail.com',false,'Ricardo','Huaripata','$2a$10$aYmWtGxCvj334WAGqD9eTuT1C6ApknkpI7.oZ3m9NENs4qswL1ZMK','ROLE_ADMIN');

-- PRODUCTOS

INSERT INTO public.products (id,date_created,date_last_modified,description,slug,title,category_id) VALUES
	 ('2d271f27-f6be-4357-bac1-f92289cb2ddc','2023-11-16 02:14:04.682','2023-11-16 02:14:04.682','Inspirada en Mortal Kombat y dibujada por Sier. La típica que tu madre ve y dice: ''ay niño, qué violento''.','fatality-hoodie','FATALITY - Hoodie','dad347c3-7ea6-457e-9bf4-b8b3f976c812'),
	 ('4e3a9b56-afea-4402-b9a2-0de47db43cae','2023-11-16 02:16:39.611','2023-11-16 02:16:39.611','No es lo mismo ponerse al fresquito, que ponerse Alfred Hitchcock. Y a nadie le gusta pasar frío. Así que sudaderón al canto.','alfred-hoodie','ALFRED - Hoodie','dad347c3-7ea6-457e-9bf4-b8b3f976c812'),
	 ('6bd50db7-7cda-4790-b535-6442d8e4ca7d','2023-11-16 02:18:19.009','2023-11-16 02:18:19.009','Inspirada en Mortal Kombat y dibujada por Sier. La típica que tu madre ve y dice: ''ay niño, qué violento''.','fatality-camiseta','FATALITY - Camiseta','dad347c3-7ea6-457e-9bf4-b8b3f976c812'),
	 ('2bddba2c-50cf-4181-b57c-e3cea93a8703','2023-11-16 02:19:00.32','2023-11-16 02:19:00.32','No es lo mismo ponerse al fresquito, que ponerse Alfred Hitchcock. Tú sabe.','alfred-camiseta','ALFRED - Camiseta','dad347c3-7ea6-457e-9bf4-b8b3f976c812'),
	 ('8e59fb26-a56e-41b5-8553-3ac62af824a4','2023-11-16 02:20:58.369','2023-11-16 02:20:58.369','Para los que escurren el bulto.','lawyer-camiseta','LAWYER - Camiseta','dc4332d6-20b6-491a-8dc4-d5d7d96bfdee'),
	 ('f78ae437-0d2a-44f8-97f4-8ae60a1292bc','2023-11-16 02:22:02.206','2023-11-16 02:22:02.206','Para los que escurren el bulto.','lawyer-hoodie','LAWYER - Hoodie','dc4332d6-20b6-491a-8dc4-d5d7d96bfdee'),
	 ('1472d425-0a54-4f5d-b6bb-5850fa2d13b0','2023-11-16 02:23:09.32','2023-11-16 02:23:09.32','Las que nos gustan: simple y directa. Prendas en diferentes colores, dependiendo del propio color del logo.','3d-camiseta','3D LOGO - Camiseta','1480c9dc-b798-4be0-8cb3-ad196d85f0c5'),
	 ('e6c810d2-3a81-4b70-868d-9afee70f3438','2023-11-16 02:24:06.652','2023-11-16 02:24:06.652','Las que nos gustan: simple y directa. Prendas en diferentes colores, dependiendo del propio color del logo.','3d-hoodie','3D LOGO - Hoodie','1480c9dc-b798-4be0-8cb3-ad196d85f0c5'),
	 ('d7801500-37cf-492e-8cc6-39c276880fb3','2023-11-16 02:26:57.111','2023-11-16 02:26:57.111','Segunda carta de la colección. El Ermitaño está encorvado, en un sitio oscuro y con una luz alumbrándole. Básicamente como tú en tu cuarto.','the-hermit-huge-oversized-tee','THE HERMIT - Huge Oversized Tee','8e257f3e-bea9-419d-8e37-f3557e1688d8'),
	 ('7617b2ee-f102-468d-bd43-4392337f2631','2023-11-16 02:28:05.958','2023-11-16 02:28:05.958','Segunda carta de la colección. El Ermitaño está encorvado, en un sitio oscuro y con una luz alumbrándole. Básicamente como tú en tu cuarto.','the-hermit-hoodie-oversized','THE HERMIT - Hoodie Oversized','8e257f3e-bea9-419d-8e37-f3557e1688d8');
INSERT INTO public.products (id,date_created,date_last_modified,description,slug,title,category_id) VALUES
	 ('431ece23-483f-4f8e-b7a5-b7ff0b412ed6','2023-11-16 02:30:01.935','2023-11-16 02:30:01.935','Homenaje al mítico juego del año 1985 que por temas de copyright no podemos nombrar (si lo hacemos tendrían que matarnos)','tetrominos-tshirt','TETROMINOS - T-shirt','f811cd22-e617-45f2-ab04-33194e1908c5'),
	 ('0e850ecb-b0ef-4757-a69b-552c30ceb1cf','2023-11-16 02:30:30.036','2023-11-16 02:30:30.036','Homenaje al mítico juego del año 1985 que por temas de copyright no podemos nombrar (si lo hacemos tendrían que matarnos)','tetrominos-hoodie','TETROMINOS - Hoodie','f811cd22-e617-45f2-ab04-33194e1908c5'),
	 ('654fa2fd-1093-4273-9905-3beefdb4114b','2023-11-16 02:32:09.213','2023-11-16 02:32:09.213','Mantenlo simple y elegante','classy-hoodie-oversized','CLASSY - Hoodie Oversized','1ac08f16-cc9a-4bcf-a638-84ceeb4c1f80'),
	 ('41d24e2c-69f0-4a59-98bd-33f8a3c59f30','2023-11-16 02:33:03.202','2023-11-16 02:33:03.202','Lo de siempre, como siempre, pero mejor que siempre','ogs-camiseta','OG''s - Camiseta','1ac08f16-cc9a-4bcf-a638-84ceeb4c1f80'),
	 ('a7aa8214-7c8c-43f2-bf43-a9d689b5c6ca','2023-11-16 02:33:45.198','2023-11-16 02:33:45.198','Lo de siempre, como siempre, pero mejor que siempre','ogs-crewneck','THE OG''s - Crewneck','1ac08f16-cc9a-4bcf-a638-84ceeb4c1f80'),
	 ('e24bd8da-56e5-4883-9556-f96499c3367a','2023-11-16 02:34:33.095','2023-11-16 02:34:33.095','Lo de siempre, como siempre, pero mejor que siempre','ogs-hoodie','OG''s - Hoodie','1ac08f16-cc9a-4bcf-a638-84ceeb4c1f80');

-- COLORS

INSERT INTO public.colors (id,date_created,date_last_modified,hex_code,title) VALUES
	 ('0c912c8b-6129-4292-9da4-5fbc6dc24682','2023-11-16 02:08:15.211','2023-11-16 02:08:15.211','000000','negro'),
	 ('890e6b76-c264-4520-b70e-d564c62096f8','2023-11-16 02:08:36.493','2023-11-16 02:08:36.493','FFFFFF','blanco'),
	 ('7d74d59a-7799-4d64-a7ea-5bf011601dc9','2023-11-16 02:09:09.147','2023-11-16 02:09:09.147','FF0000','rojo'),
	 ('809b79d0-f039-4f34-bfcc-d6580058a8e4','2023-11-16 02:09:48.343','2023-11-16 02:09:48.343','026F00','verde'),
	 ('a80f8f95-a023-43f0-bb7b-00c6b4f656a9','2023-11-16 02:10:04.257','2023-11-16 02:10:04.257','0005B0','azul'),
	 ('a20f9468-53c2-4d8d-9941-ceb5c87c1de8','2023-11-16 02:10:59.47','2023-11-16 02:10:59.47','EDF028','amarillo'),
	 ('8214a81f-57d2-4665-aab1-fbd15180e679','2023-11-16 02:11:46.053','2023-11-16 02:11:46.053','28F0F0','turquesa'),
	 ('0b52d326-d9b1-43be-9c78-c79f7a4eb888','2023-11-16 02:21:33.804','2023-11-16 02:21:33.804','C5C5C5','gris');

-- CATEGORIAS

INSERT INTO public.categories (id,date_created,date_last_modified,featured_image_url,slug,title) VALUES
	 ('dad347c3-7ea6-457e-9bf4-b8b3f976c812','2023-11-15 20:18:32.591','2023-11-15 20:18:32.591','https://ogsbrand.storage.googleapis.com/media/images/OGS_OCT23_Clasicos_Banner.2e16d0ba.fill-700x930-c100.jpg','los-clasicos','LOS CLÁSICOS'),
	 ('dc4332d6-20b6-491a-8dc4-d5d7d96bfdee','2023-11-16 02:00:36.072','2023-11-16 02:00:36.072','https://ogsbrand.storage.googleapis.com/media/images/Copy_of_OGS_BTS23_Banner_Lawy.2e16d0ba.fill-700x930-c100.jpg','lawyer','LAWYER'),
	 ('1480c9dc-b798-4be0-8cb3-ad196d85f0c5','2023-11-16 02:02:12.858','2023-11-16 02:02:12.858','https://ogsbrand.storage.googleapis.com/media/images/Copy_of_OGS_BTS23_Banner_3D2.2e16d0ba.fill-700x930-c100.jpg','3d','3D LOGO'),
	 ('8e257f3e-bea9-419d-8e37-f3557e1688d8','2023-11-16 02:02:43.641','2023-11-16 02:02:43.641','https://ogsbrand.storage.googleapis.com/media/images/Copy_of_OGS_BTS23_Banner_Herm.2e16d0ba.fill-700x930-c100.jpg','the-hermit','THE HERMIT'),
	 ('f811cd22-e617-45f2-ab04-33194e1908c5','2023-11-16 02:03:14.511','2023-11-16 02:03:14.511','https://ogsbrand.storage.googleapis.com/media/images/Copy_of_OGS_BTS23_Banner_Tetr.2e16d0ba.fill-700x930-c100.jpg','tetrominos','TETROMINOS'),
	 ('1ac08f16-cc9a-4bcf-a638-84ceeb4c1f80','2023-11-16 02:04:18.104','2023-11-16 02:04:18.104','https://ogsbrand.storage.googleapis.com/media/images/Copy_of_OGS_TheBasics23_Banne.2e16d0ba.fill-700x930-c100.jpg','new-basics','THE BASICS');
