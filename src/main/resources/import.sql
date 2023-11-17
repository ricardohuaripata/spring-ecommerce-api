
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

INSERT INTO public.shipping_addresses (id,address,city,contact_phone,country,date_created,date_last_modified,firstname,lastname,postal_code,user_id) VALUES
	 ('43eafcb9-dcc8-4454-9b00-0e2bd31a80ed','Calle Mercerdes Benz, Nº 43, 1ºB','Sevilla','63020913','España','2023-11-16 17:41:07.051','2023-11-16 17:41:07.051','Ricardo','Huaripata','41010','07ea76e0-142f-499a-929f-bedb08c79624'),
	 ('add11502-1346-4732-a004-fb8af72df9b6','Calle Asunción, Nº 21, 1ºB','Sevilla','63020913','España','2023-11-16 17:42:09.737','2023-11-16 17:42:09.737','Ricardo','Huaripata','40105','07ea76e0-142f-499a-929f-bedb08c79624'),
	 ('18009334-0d7d-4a2a-a576-bce3597c5e3f','Calle Juan Alberto, Nº 13, 2ºA','Málaga','77040813','España','2023-11-16 17:43:16.549','2023-11-16 17:43:16.549','Ricardo','Huaripata','49023','07ea76e0-142f-499a-929f-bedb08c79624');

-- CATEGORIAS

INSERT INTO public.categories (id,date_created,date_last_modified,featured_image_url,slug,title) VALUES
	 ('dad347c3-7ea6-457e-9bf4-b8b3f976c812','2023-11-15 20:18:32.591','2023-11-15 20:18:32.591','https://ogsbrand.storage.googleapis.com/media/images/OGS_OCT23_Clasicos_Banner.2e16d0ba.fill-700x930-c100.jpg','los-clasicos','LOS CLÁSICOS'),
	 ('dc4332d6-20b6-491a-8dc4-d5d7d96bfdee','2023-11-16 02:00:36.072','2023-11-16 02:00:36.072','https://ogsbrand.storage.googleapis.com/media/images/Copy_of_OGS_BTS23_Banner_Lawy.2e16d0ba.fill-700x930-c100.jpg','lawyer','LAWYER'),
	 ('1480c9dc-b798-4be0-8cb3-ad196d85f0c5','2023-11-16 02:02:12.858','2023-11-16 02:02:12.858','https://ogsbrand.storage.googleapis.com/media/images/Copy_of_OGS_BTS23_Banner_3D2.2e16d0ba.fill-700x930-c100.jpg','3d','3D LOGO'),
	 ('8e257f3e-bea9-419d-8e37-f3557e1688d8','2023-11-16 02:02:43.641','2023-11-16 02:02:43.641','https://ogsbrand.storage.googleapis.com/media/images/Copy_of_OGS_BTS23_Banner_Herm.2e16d0ba.fill-700x930-c100.jpg','the-hermit','THE HERMIT'),
	 ('f811cd22-e617-45f2-ab04-33194e1908c5','2023-11-16 02:03:14.511','2023-11-16 02:03:14.511','https://ogsbrand.storage.googleapis.com/media/images/Copy_of_OGS_BTS23_Banner_Tetr.2e16d0ba.fill-700x930-c100.jpg','tetrominos','TETROMINOS'),
	 ('1ac08f16-cc9a-4bcf-a638-84ceeb4c1f80','2023-11-16 02:04:18.104','2023-11-16 02:04:18.104','https://ogsbrand.storage.googleapis.com/media/images/Copy_of_OGS_TheBasics23_Banne.2e16d0ba.fill-700x930-c100.jpg','new-basics','THE BASICS');

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

-- VARIACIONES PRODUCTO-COLOR

INSERT INTO public.color_product_variants (id,back_image_url,base_price,date_created,date_last_modified,final_price,front_image_url,color_id,product_id) VALUES
	 ('571a42a0-4dcb-4a21-a8c8-f13852850ffc','https://ogsbrand.storage.googleapis.com/media/images/Copy_of_OGS_BTS2023_FATALIT.2e16d0ba.fill-1200x1400-c100_tiIgPe3.jpg',42.99,'2023-11-16 16:51:06.392','2023-11-16 16:51:06.392',42.99,'https://ogsbrand.storage.googleapis.com/media/images/Copy_of_OGS_BTS2023_FATALIT.2e16d0ba.fill-1200x1400-c100_zZlP6Pa.jpg','890e6b76-c264-4520-b70e-d564c62096f8','2d271f27-f6be-4357-bac1-f92289cb2ddc'),
	 ('bcd80b22-bd91-485a-80c1-0a105d14727e','https://ogsbrand.storage.googleapis.com/media/images/Copy_of_OGS_BTS2023_HITCHCO.2e16d0ba.fill-1200x1400-c100_q2O3mAB.jpg',42.99,'2023-11-16 16:55:03.9','2023-11-16 16:55:03.9',42.99,'https://ogsbrand.storage.googleapis.com/media/images/Copy_of_OGS_BTS2023_HITCHCO.2e16d0ba.fill-1200x1400-c100_ldt6GDv.jpg','890e6b76-c264-4520-b70e-d564c62096f8','4e3a9b56-afea-4402-b9a2-0de47db43cae'),
	 ('a04a076c-54a0-4272-adc0-2fc2a16a8cf9','https://ogsbrand.storage.googleapis.com/media/images/Copy_of_OGS_BTS2023_THEHERM.2e16d0ba.fill-1200x1400-c100_rf5JAYL.jpg',34.99,'2023-11-16 16:59:46.871','2023-11-16 16:59:46.871',34.99,'https://ogsbrand.storage.googleapis.com/media/images/Copy_of_OGS_BTS2023_THEHERM.2e16d0ba.fill-1200x1400-c100_q9lzMCX.jpg','890e6b76-c264-4520-b70e-d564c62096f8','d7801500-37cf-492e-8cc6-39c276880fb3'),
	 ('d570a60e-6b24-484c-92e6-b6ceeb9eb3aa','https://ogsbrand.storage.googleapis.com/media/images/Copy_of_OGS_BTS2023_THEHERMIT.2e16d0ba.fill-800x933-c100_pUdQPNA.jpg',34.99,'2023-11-16 16:58:23.011','2023-11-16 16:58:23.011',34.99,'https://ogsbrand.storage.googleapis.com/media/images/Copy_of_OGS_BTS2023_THEHERMIT.2e16d0ba.fill-800x933-c100_HR6dhun.jpg','0c912c8b-6129-4292-9da4-5fbc6dc24682','d7801500-37cf-492e-8cc6-39c276880fb3'),
	 ('01326f52-c1a8-40e9-8c09-ff5b54b52321','https://ogsbrand.storage.googleapis.com/media/images/Copy_of_OGS_BTS2023_HITCHCOCK.2e16d0ba.fill-800x933-c100_8MJutQJ.jpg',42.99,'2023-11-16 16:55:53.475','2023-11-16 16:55:53.475',42.99,'https://ogsbrand.storage.googleapis.com/media/images/Copy_of_OGS_BTS2023_HITCHCOCK.2e16d0ba.fill-800x933-c100_gNkD7KC.jpg','0c912c8b-6129-4292-9da4-5fbc6dc24682','4e3a9b56-afea-4402-b9a2-0de47db43cae'),
	 ('aa094db1-fe29-4fc8-b0f8-35e51456ceb6','https://ogsbrand.storage.googleapis.com/media/images/Copy_of_OGS_BTS2023_FATALITY2.2e16d0ba.fill-800x933-c100_WiKSFXV.jpg',42.99,'2023-11-16 16:52:18.987','2023-11-16 16:52:18.987',42.99,'https://ogsbrand.storage.googleapis.com/media/images/Copy_of_OGS_BTS2023_FATALITY2.2e16d0ba.fill-800x933-c100_PLn55sQ.jpg','0c912c8b-6129-4292-9da4-5fbc6dc24682','2d271f27-f6be-4357-bac1-f92289cb2ddc'),
	 ('ee8f5da4-49f4-4f38-87c1-45fc866ca961','https://ogsbrand.storage.googleapis.com/media/images/Copy_of_OGS_BTS2023_LAWYER_Ca.2e16d0ba.fill-800x933-c100_MgPKIaH.jpg',26.99,'2023-11-16 17:11:51.182','2023-11-16 17:11:51.182',26.99,'https://ogsbrand.storage.googleapis.com/media/images/Copy_of_OGS_BTS2023_LAWYER_Ca.2e16d0ba.fill-800x933-c100.jpg','890e6b76-c264-4520-b70e-d564c62096f8','8e59fb26-a56e-41b5-8553-3ac62af824a4'),
	 ('d0daa547-57d7-40ad-96d4-43e3bd8116e5','https://ogsbrand.storage.googleapis.com/media/images/Copy_of_OGS_BTS2023_LAWYER_Ca.2e16d0ba.fill-800x933-c100_fjelrcc.jpg',26.99,'2023-11-16 17:12:36.779','2023-11-16 17:12:36.779',26.99,'https://ogsbrand.storage.googleapis.com/media/images/Copy_of_OGS_BTS2023_LAWYER_Ca.2e16d0ba.fill-800x933-c100_GQ2ph0x.jpg','0c912c8b-6129-4292-9da4-5fbc6dc24682','8e59fb26-a56e-41b5-8553-3ac62af824a4'),
	 ('14286f1b-8272-4dd3-8217-16f9141496fb','https://ogsbrand.storage.googleapis.com/media/images/Copy_of_OGS_BTS2023_LAWYER_Ca.2e16d0ba.fill-800x933-c100_cPe38Q4.jpg',26.99,'2023-11-16 17:14:07.604','2023-11-16 17:14:07.604',26.99,'https://ogsbrand.storage.googleapis.com/media/images/Copy_of_OGS_BTS2023_LAWYER_Ca.2e16d0ba.fill-800x933-c100_xQIZcTY.jpg','0b52d326-d9b1-43be-9c78-c79f7a4eb888','8e59fb26-a56e-41b5-8553-3ac62af824a4'),
	 ('d1f60e85-267d-492c-9e94-55efb235e892','https://ogsbrand.storage.googleapis.com/media/images/Copy_of_OGS_BTS2023_LAWYER_Ca.2e16d0ba.fill-800x933-c100_ivxTIcN.jpg',26.99,'2023-11-16 17:14:59.331','2023-11-16 17:14:59.331',26.99,'https://ogsbrand.storage.googleapis.com/media/images/Copy_of_OGS_BTS2023_LAWYER_Ca.2e16d0ba.fill-800x933-c100_hkPfCSV.jpg','809b79d0-f039-4f34-bfcc-d6580058a8e4','8e59fb26-a56e-41b5-8553-3ac62af824a4');

-- VARIACIONES PRODUCTO-TALLA-COLOR

INSERT INTO public.size_color_product_variants (id,date_created,date_last_modified,"size",stock,color_product_variant_id) VALUES
	 ('e7165b8e-cb68-4ad5-9094-887e3f94a924','2023-11-16 19:09:06.43','2023-11-16 19:09:06.43','S',99,'571a42a0-4dcb-4a21-a8c8-f13852850ffc'),
	 ('2d2f18b8-263d-473e-8e63-d2c36ea89b40','2023-11-16 19:09:28.03','2023-11-16 19:09:28.03','M',99,'571a42a0-4dcb-4a21-a8c8-f13852850ffc'),
	 ('08849d75-6b02-4c0d-a1fb-715fb77bfebb','2023-11-16 19:09:36.222','2023-11-16 19:09:36.222','L',99,'571a42a0-4dcb-4a21-a8c8-f13852850ffc'),
	 ('757d3745-0168-4d1c-a699-195fca04947f','2023-11-16 19:09:40.532','2023-11-16 19:09:40.532','XL',99,'571a42a0-4dcb-4a21-a8c8-f13852850ffc'),
	 ('2e84e04f-e73d-4c1b-a337-d5db1d9f3b69','2023-11-16 19:09:43.754','2023-11-16 19:09:43.754','XXL',99,'571a42a0-4dcb-4a21-a8c8-f13852850ffc'),
	 ('1b952080-ca8f-45ff-9d85-c5ca82912cc1','2023-11-16 19:10:37.752','2023-11-16 19:10:37.752','S',99,'bcd80b22-bd91-485a-80c1-0a105d14727e'),
	 ('6a70a1f8-ed11-4a2f-9b5d-48cad9b1e8c7','2023-11-16 19:10:54.826','2023-11-16 19:10:54.826','M',99,'bcd80b22-bd91-485a-80c1-0a105d14727e'),
	 ('8ea41ddd-1012-41e0-add0-a062412f7b92','2023-11-16 19:10:58.083','2023-11-16 19:10:58.083','L',99,'bcd80b22-bd91-485a-80c1-0a105d14727e'),
	 ('66e31574-b9f5-4d3a-9b84-7822dbc46f3a','2023-11-16 19:11:00.218','2023-11-16 19:11:00.218','XL',99,'bcd80b22-bd91-485a-80c1-0a105d14727e'),
	 ('1097104d-25bd-4630-b12c-8ee8b22b78a8','2023-11-16 19:11:02.397','2023-11-16 19:11:02.397','XXL',99,'bcd80b22-bd91-485a-80c1-0a105d14727e');
INSERT INTO public.size_color_product_variants (id,date_created,date_last_modified,"size",stock,color_product_variant_id) VALUES
	 ('4c78c595-bc02-49d1-9127-9c13ebc70a53','2023-11-16 19:11:11.706','2023-11-16 19:11:11.706','S',99,'a04a076c-54a0-4272-adc0-2fc2a16a8cf9'),
	 ('a5cd9cd5-addd-4dfd-99f4-de04f02870f5','2023-11-16 19:11:15.098','2023-11-16 19:11:15.098','M',99,'a04a076c-54a0-4272-adc0-2fc2a16a8cf9'),
	 ('ad7765cf-40d7-4f55-b7b8-56af925aa8db','2023-11-16 19:11:17.756','2023-11-16 19:11:17.756','L',99,'a04a076c-54a0-4272-adc0-2fc2a16a8cf9'),
	 ('87e2c0da-e810-4aed-ab37-bdab9afd3a9a','2023-11-16 19:11:19.768','2023-11-16 19:11:19.768','XL',99,'a04a076c-54a0-4272-adc0-2fc2a16a8cf9'),
	 ('7c0a47a2-d618-4ccb-b2b6-dce040cb7dc8','2023-11-16 19:11:21.649','2023-11-16 19:11:21.649','XXL',99,'a04a076c-54a0-4272-adc0-2fc2a16a8cf9'),
	 ('3891ad0c-55e0-4d2a-acee-eea76de77613','2023-11-16 19:11:34.217','2023-11-16 19:11:34.217','S',99,'d570a60e-6b24-484c-92e6-b6ceeb9eb3aa'),
	 ('56ab53ed-2321-46ea-bc45-90e313dfa1a0','2023-11-16 19:11:36.829','2023-11-16 19:11:36.829','M',99,'d570a60e-6b24-484c-92e6-b6ceeb9eb3aa'),
	 ('3f568c1c-52f6-4e48-ae71-6a3e3ea305e4','2023-11-16 19:11:40.419','2023-11-16 19:11:40.419','L',99,'d570a60e-6b24-484c-92e6-b6ceeb9eb3aa'),
	 ('4a08cf9f-e503-47ce-a8b5-f8531635c46f','2023-11-16 19:11:42.495','2023-11-16 19:11:42.495','XL',99,'d570a60e-6b24-484c-92e6-b6ceeb9eb3aa'),
	 ('06ba425e-9b4d-4dcf-b204-dfe17246db00','2023-11-16 19:11:44.505','2023-11-16 19:11:44.505','XXL',99,'d570a60e-6b24-484c-92e6-b6ceeb9eb3aa');
INSERT INTO public.size_color_product_variants (id,date_created,date_last_modified,"size",stock,color_product_variant_id) VALUES
	 ('ca1703a0-4a50-4df0-a382-db5c090192d5','2023-11-16 19:11:54.291','2023-11-16 19:11:54.291','S',99,'01326f52-c1a8-40e9-8c09-ff5b54b52321'),
	 ('f5a8b11c-5ed7-4e0d-aedc-48c5b60fa3a7','2023-11-16 19:11:56.797','2023-11-16 19:11:56.797','M',99,'01326f52-c1a8-40e9-8c09-ff5b54b52321'),
	 ('14c9324c-e7c3-4cec-b26e-09c58a9825b1','2023-11-16 19:11:59.629','2023-11-16 19:11:59.629','L',99,'01326f52-c1a8-40e9-8c09-ff5b54b52321'),
	 ('1ba02e4a-bbee-4e58-9e0e-cef1d79c970f','2023-11-16 19:12:01.652','2023-11-16 19:12:01.652','XL',99,'01326f52-c1a8-40e9-8c09-ff5b54b52321'),
	 ('4da53d0b-c14e-450a-a761-05b44a963acf','2023-11-16 19:12:03.65','2023-11-16 19:12:03.65','XXL',99,'01326f52-c1a8-40e9-8c09-ff5b54b52321'),
	 ('ab3709f3-fda5-4360-8935-2c74c93cf53f','2023-11-16 19:12:10.918','2023-11-16 19:12:10.918','S',99,'aa094db1-fe29-4fc8-b0f8-35e51456ceb6'),
	 ('16769705-0f8f-421d-90d6-da2312b6d308','2023-11-16 19:12:13.651','2023-11-16 19:12:13.651','M',99,'aa094db1-fe29-4fc8-b0f8-35e51456ceb6'),
	 ('5213e87d-7263-4e88-8e42-ebd7552d63ec','2023-11-16 19:12:41.729','2023-11-16 19:12:41.729','L',99,'aa094db1-fe29-4fc8-b0f8-35e51456ceb6'),
	 ('c5390f8e-2afe-4d49-8698-1846b13f487e','2023-11-16 19:12:43.47','2023-11-16 19:12:43.47','XL',99,'aa094db1-fe29-4fc8-b0f8-35e51456ceb6'),
	 ('5f3b3d65-3e44-426b-807d-90201b0ce2f7','2023-11-16 19:12:45.352','2023-11-16 19:12:45.352','XXL',99,'aa094db1-fe29-4fc8-b0f8-35e51456ceb6');
INSERT INTO public.size_color_product_variants (id,date_created,date_last_modified,"size",stock,color_product_variant_id) VALUES
	 ('90a7b5d9-9ebc-473f-9127-e0dfac4a777a','2023-11-16 19:13:03.775','2023-11-16 19:13:03.775','S',99,'ee8f5da4-49f4-4f38-87c1-45fc866ca961'),
	 ('836fba90-4112-4a53-8039-4905e138eac1','2023-11-16 19:13:06.283','2023-11-16 19:13:06.283','M',99,'ee8f5da4-49f4-4f38-87c1-45fc866ca961'),
	 ('5c8e5e8f-3ad1-40f6-9f20-b2fea1edb89c','2023-11-16 19:13:08.68','2023-11-16 19:13:08.68','L',99,'ee8f5da4-49f4-4f38-87c1-45fc866ca961'),
	 ('ae0537b4-1401-439d-a814-967e9aa8fa47','2023-11-16 19:13:10.63','2023-11-16 19:13:10.63','XL',99,'ee8f5da4-49f4-4f38-87c1-45fc866ca961'),
	 ('e0d9472e-9871-4577-a551-9b2486de8cfc','2023-11-16 19:13:12.467','2023-11-16 19:13:12.467','XXL',99,'ee8f5da4-49f4-4f38-87c1-45fc866ca961'),
	 ('d00da59c-1c7f-4ccf-a0a3-c757937ab991','2023-11-16 19:13:19.225','2023-11-16 19:13:19.225','S',99,'d0daa547-57d7-40ad-96d4-43e3bd8116e5'),
	 ('05806f42-5e97-4086-9d4a-71a27a721656','2023-11-16 19:13:21.875','2023-11-16 19:13:21.875','M',99,'d0daa547-57d7-40ad-96d4-43e3bd8116e5'),
	 ('6b3fc188-2dc7-45f8-8cf4-edb38dba9e97','2023-11-16 19:13:24.649','2023-11-16 19:13:24.649','L',99,'d0daa547-57d7-40ad-96d4-43e3bd8116e5'),
	 ('3838c789-a8eb-4327-b7b8-5cf10e8f3f04','2023-11-16 19:13:26.538','2023-11-16 19:13:26.538','XL',99,'d0daa547-57d7-40ad-96d4-43e3bd8116e5'),
	 ('f607425e-1f29-4fb5-8829-0904ed2e8f6c','2023-11-16 19:13:28.295','2023-11-16 19:13:28.295','XXL',99,'d0daa547-57d7-40ad-96d4-43e3bd8116e5');
INSERT INTO public.size_color_product_variants (id,date_created,date_last_modified,"size",stock,color_product_variant_id) VALUES
	 ('4d70a56b-16d3-4bd8-8fec-dc98215f5ee5','2023-11-16 19:13:35.878','2023-11-16 19:13:35.878','S',99,'14286f1b-8272-4dd3-8217-16f9141496fb'),
	 ('3c3ea635-0813-420c-a2d9-5fceafee8c41','2023-11-16 19:13:38.422','2023-11-16 19:13:38.422','M',99,'14286f1b-8272-4dd3-8217-16f9141496fb'),
	 ('4f436db9-5e02-41bb-adad-4b841ece6d1b','2023-11-16 19:13:40.933','2023-11-16 19:13:40.933','L',99,'14286f1b-8272-4dd3-8217-16f9141496fb'),
	 ('0b6e79fc-eb17-430d-ad9b-d4d4b6d23750','2023-11-16 19:13:44.452','2023-11-16 19:13:44.452','XL',99,'14286f1b-8272-4dd3-8217-16f9141496fb'),
	 ('dd25075d-4d2d-4af8-9ddf-b07651d0b84a','2023-11-16 19:13:46.14','2023-11-16 19:13:46.14','XXL',99,'14286f1b-8272-4dd3-8217-16f9141496fb'),
	 ('40099294-24c7-42ed-8a85-2254fcae27f4','2023-11-16 19:13:53.689','2023-11-16 19:13:53.689','S',99,'d1f60e85-267d-492c-9e94-55efb235e892'),
	 ('bb47d07e-4697-433e-9834-c274327d9bf1','2023-11-16 19:14:22.253','2023-11-16 19:14:22.253','M',99,'d1f60e85-267d-492c-9e94-55efb235e892'),
	 ('c394a045-ec0e-4c41-b4a4-dec616e86d3f','2023-11-16 19:14:25.215','2023-11-16 19:14:25.215','L',99,'d1f60e85-267d-492c-9e94-55efb235e892'),
	 ('63bf34ce-759e-44ea-91b4-7c37a9965d19','2023-11-16 19:14:28.733','2023-11-16 19:14:28.733','XL',99,'d1f60e85-267d-492c-9e94-55efb235e892'),
	 ('0759dd0a-5b3b-4169-a422-f78fbf24a7c5','2023-11-16 19:14:32.532','2023-11-16 19:14:32.532','XXL',99,'d1f60e85-267d-492c-9e94-55efb235e892');
