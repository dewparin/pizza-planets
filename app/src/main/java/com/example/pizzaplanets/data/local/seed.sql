-- Seed data, for backup purpose

-- Moon (Earth's satellite)
INSERT INTO planets (id, planet_code, name, description, travel_duration_ms)
VALUES (
    1,
    'sat-1',
    'Moon',
    'Welcome to our closest cosmic kitchen! Moon Base Pizzeria serves up legendary low-gravity pies where the cheese stretches all the way to the ceiling. Try our signature "Crater Crust Supreme" — baked in ancient volcanic vents for that extra smoky flavor. Fun fact: our dough rises 6x higher here thanks to 1/6th Earth gravity. Every bite is literally out of this world!',
    13000
);

-- Mercury
INSERT INTO planets (id, planet_code, name, description, travel_duration_ms)
VALUES (
    2,
    'pln-1',
    'Mercury',
    'The fastest planet gets the fastest pizza! Mercury Magma Grill is famous for stone-fired pies cooked directly on the sun-facing surface at 430°C — no oven needed. Try our "Speedy Gonzola" topped with triple mozzarella that melts before it even hits the crust. Warning: delivery to the night side may arrive frozen. We recommend dining on the terminator line for the perfect temperature!',
    18000
);

-- Venus
INSERT INTO planets (id, planet_code, name, description, travel_duration_ms)
VALUES (
    3,
    'pln-2',
    'Venus',
    'At Venus Cloud Nine Pizzeria, we bake at atmospheric pressure 90x stronger than Earth — giving our crusts an unmatched density and crunch. Our bestseller "Acid Rain-bow" features layers of tangy sauce inspired by our sulfuric skies. Dining happens in floating sky stations above the clouds where the view is absolutely gorgeous. Don''t worry, we provide heat-resistant bibs!',
    22000
);

-- Mars
INSERT INTO planets (id, planet_code, name, description, travel_duration_ms)
VALUES (
    4,
    'pln-4',
    'Mars',
    'The Red Planet, the Red Sauce! Mars Olympus Pizzeria sits at the peak of the tallest volcano in the solar system. Our "Olympus Mons Monster" is a 26km-tall stack of toppings that takes three Martian sols to finish. Voted #1 pizza destination by Space Yelp five years running. Pair it with our rusty iron-mineral water — an acquired taste, but hey, you''re on Mars!',
    25000
);

-- Jupiter
INSERT INTO planets (id, planet_code, name, description, travel_duration_ms)
VALUES (
    5,
    'pln-5',
    'Jupiter',
    'Go big or go home! Jupiter''s Great Red Spot Pizzeria serves the largest pies in the solar system — each one wider than two Earths. Our "Gas Giant Gorgonzola" floats on a cushion of hydrogen and helium for a truly airy crust experience. The storm-spun dough has been rising for over 300 years. Pro tip: order the "Eye of the Storm" — it''s calmer and the toppings don''t fly off!',
    35000
);

-- Saturn
INSERT INTO planets (id, planet_code, name, description, travel_duration_ms)
VALUES (
    6,
    'pln-6',
    'Saturn',
    'Every pizza comes with a ring! Saturn Ring-Side Pizzeria is the most Instagrammable restaurant in the galaxy. Our signature "Ring-a-Roni" features concentric rings of pepperoni, olives, and mushrooms orbiting a mozzarella core. Dine on our terrace with a panoramic view of the rings while ice crystals gently drift past your table. Free breadstick ring for every order!',
    40000
);

-- Uranus
INSERT INTO planets (id, planet_code, name, description, travel_duration_ms)
VALUES (
    7,
    'pln-7',
    'Uranus',
    'Everything''s a little tilted here — including our pizza! Uranus Sideways Slice serves pies on a 98-degree angle for a dining experience like no other. Our famous "Ice Giant Gelato Pizza" is a dessert pie topped with methane-blue gelato and diamond sprinkles (yes, it rains diamonds here). The restaurant rotates so every seat gets a window view. Just don''t drop your slice — it rolls forever!',
    50000
);

-- Neptune
INSERT INTO planets (id, planet_code, name, description, travel_duration_ms)
VALUES (
    8,
    'pln-8',
    'Neptune',
    'The most remote pizzeria in the solar system — but worth every light-minute of travel! Neptune Deep Blue Pizzeria features our award-winning "Supersonic Supreme" kneaded by 2,100 km/h winds for the chewiest crust you''ve ever tasted. The deep blue ambiance is unmatched for a romantic pizza date. Our motto: "If you''ve come this far, the pizza better be legendary." It is.',
    60000
);

-- ==========================================
-- MOON (planet_id = 1)
-- ==========================================
INSERT INTO pizzas (id, planet_id, name, description)
VALUES (1, 1, 'Crater Crust Supreme', 'Our legendary signature pie baked in ancient lunar volcanic vents. Loaded with smoked mozzarella, roasted garlic, and a ring of crispy crust shaped like a crater rim. The smoky flavor is literally geological.');

INSERT INTO pizzas (id, planet_id, name, description)
VALUES (2, 1, 'The Dark Side Deluxe', 'A mysterious half-and-half pizza — one side blazing hot with jalapeños and ghost pepper sauce, the other cool with ricotta and fresh basil. You never know which side you''ll bite into first.');

INSERT INTO pizzas (id, planet_id, name, description)
VALUES (3, 1, 'Low-G Cheese Pull', 'Quadruple mozzarella, provolone, and gouda on a pillowy dough that rises 6x higher in lunar gravity. The cheese pull stretches to the ceiling. Literally. We''ve measured.');

INSERT INTO pizzas (id, planet_id, name, description)
VALUES (4, 1, 'Apollo Classic', 'A tribute to the first humans who visited. Simple, timeless, and reliable — tomato sauce, fresh mozzarella, basil, and a drizzle of olive oil. One small bite for man, one giant flavor for mankind.');

-- ==========================================
-- MERCURY (planet_id = 2)
-- ==========================================
INSERT INTO pizzas (id, planet_id, name, description)
VALUES (5, 2, 'Speedy Gonzola', 'Triple mozzarella and gorgonzola on a thin crust that cooks in 3 seconds flat on Mercury''s sun-facing surface. The fastest pizza in the solar system. Blink and you''ll miss the bake.');

INSERT INTO pizzas (id, planet_id, name, description)
VALUES (6, 2, 'Solar Flare BBQ', 'Pulled pork, caramelized onions, and smoky BBQ sauce kissed by actual solar radiation. Comes with SPF 5000 dipping sauce on the side. Wear your shades while eating.');

INSERT INTO pizzas (id, planet_id, name, description)
VALUES (7, 2, 'Terminator Line Special', 'Half fire-roasted veggies from the day side, half frozen burrata from the night side, meeting perfectly at the crust. Best enjoyed at the boundary where hot meets cold.');

INSERT INTO pizzas (id, planet_id, name, description)
VALUES (8, 2, 'Iron Core Meat Lovers', 'Pepperoni, sausage, bacon, and ham piled high on an iron-infused black crust. Dense, heavy, and packed with flavor — just like Mercury''s oversized metallic core.');

-- ==========================================
-- VENUS (planet_id = 3)
-- ==========================================
INSERT INTO pizzas (id, planet_id, name, description)
VALUES (9, 3, 'Acid Rain-bow', 'Layers of tangy tomato, pickled peppers, lemon zest ricotta, and a sulfur-yellow turmeric drizzle. Inspired by the Venusian skies. Sour lovers, this is your paradise.');

INSERT INTO pizzas (id, planet_id, name, description)
VALUES (10, 3, 'Pressure Cooker Deep Dish', 'Baked under 90 atmospheres of pressure for the densest, crunchiest deep dish you''ve ever experienced. Stuffed with three cheeses, spinach, and roasted tomatoes. You''ll need both hands.');

INSERT INTO pizzas (id, planet_id, name, description)
VALUES (11, 3, 'Cloud Nine Margherita', 'A lighter-than-air margherita served at our floating sky station. Whipped mozzarella, San Marzano tomatoes, and fresh basil on a cloud-puffed crust. Dining above the chaos never tasted so good.');

INSERT INTO pizzas (id, planet_id, name, description)
VALUES (12, 3, 'Greenhouse Veggie Inferno', 'A fully loaded veggie pizza with roasted bell peppers, sun-dried tomatoes, artichokes, and chili flakes. Named after Venus''s runaway greenhouse effect. Hot, hotter, Venus.');

-- ==========================================
-- MARS (planet_id = 4)
-- ==========================================
INSERT INTO pizzas (id, planet_id, name, description)
VALUES (13, 4, 'Olympus Mons Monster', 'A towering stack of pepperoni, mushrooms, olives, sausage, and four cheeses piled as high as the tallest volcano in the solar system. Comes with a tiny flag to plant on top when you summit.');

INSERT INTO pizzas (id, planet_id, name, description)
VALUES (14, 4, 'Red Planet Pepperoni', 'Classic pepperoni on a red-tinted crust made with Martian iron-oxide flour. Rusty in color, impeccable in taste. The bestseller on Mars five sols running.');

INSERT INTO pizzas (id, planet_id, name, description)
VALUES (15, 4, 'Curiosity Rover Combo', 'A little bit of everything — because curiosity means trying it all. Pepperoni, pineapple, anchovies, jalapeños, and chocolate drizzle. Sounds wrong. Tastes revolutionary.');

INSERT INTO pizzas (id, planet_id, name, description)
VALUES (16, 4, 'Valles Marineris Calzone', 'A calzone so long it rivals the deepest canyon in the solar system. Stuffed with ricotta, mozzarella, ham, and a river of marinara running through the center. Bring a friend. Or three.');

INSERT INTO pizzas (id, planet_id, name, description)
VALUES (17, 4, 'Terraform Truffle', 'White truffle cream base, wild mushrooms, arugula, and shaved parmesan on a sourdough crust. Elegant enough to make Mars feel like home. Terraforming starts with good food.');

-- ==========================================
-- JUPITER (planet_id = 5)
-- ==========================================
INSERT INTO pizzas (id, planet_id, name, description)
VALUES (18, 5, 'Great Red Spot Diavola', 'Spicy salami, nduja, Calabrian chili, and hot honey on a tomato base as fiery as Jupiter''s 300-year-old storm. Size: two Earths wide. We recommend sharing.');

INSERT INTO pizzas (id, planet_id, name, description)
VALUES (19, 5, 'Gas Giant Gorgonzola', 'A feather-light hydrogen-puffed crust topped with creamy gorgonzola, caramelized pears, and walnuts. Floats off the plate if you''re not careful. Tastes like a dream in zero-g.');

INSERT INTO pizzas (id, planet_id, name, description)
VALUES (20, 5, 'Eye of the Storm', 'A calm and balanced white pizza — garlic cream base, fresh mozzarella, cherry tomatoes, and basil — designed for those who want flavor without the chaos. The peaceful center of Jupiter dining.');

INSERT INTO pizzas (id, planet_id, name, description)
VALUES (21, 5, 'Io Volcanic Crust', 'Baked on Jupiter''s moon Io using active volcanic heat. Spicy chorizo, roasted red peppers, and molten provolone on a charred sourdough base. Erupts with flavor in every bite.');

INSERT INTO pizzas (id, planet_id, name, description)
VALUES (22, 5, 'Europa Ice Fisher', 'Fresh seafood pizza inspired by the subsurface ocean of Europa. Shrimp, calamari, smoked salmon, and a lemon-dill cream base. We can neither confirm nor deny that alien fish were involved.');

-- ==========================================
-- SATURN (planet_id = 6)
-- ==========================================
INSERT INTO pizzas (id, planet_id, name, description)
VALUES (23, 6, 'Ring-a-Roni', 'Concentric rings of pepperoni, olives, mushrooms, and bell peppers orbiting a mozzarella core. Almost too beautiful to eat. Almost. Our most photographed pizza in the galaxy.');

INSERT INTO pizzas (id, planet_id, name, description)
VALUES (24, 6, 'Titan Truffle Fog', 'Black truffle, fontina, and caramelized onions under a smoky haze inspired by Titan''s thick atmosphere. Served under a glass dome that lifts to reveal the fog. Dinner and a show.');

INSERT INTO pizzas (id, planet_id, name, description)
VALUES (25, 6, 'Ice Crystal Bianca', 'A white pizza with burrata, prosciutto, and a honey-thyme drizzle, dusted with edible ice crystals harvested from Saturn''s rings. Delicate, refined, and absolutely stunning.');

INSERT INTO pizzas (id, planet_id, name, description)
VALUES (26, 6, 'Cassini Quattro Formaggi', 'Four cheeses — mozzarella, parmesan, gorgonzola, and taleggio — each representing a division of Saturn''s rings. Named after the probe that revealed their beauty to humanity.');

-- ==========================================
-- URANUS (planet_id = 7)
-- ==========================================
INSERT INTO pizzas (id, planet_id, name, description)
VALUES (27, 7, 'Ice Giant Gelato Pizza', 'A dessert masterpiece — sweet dough topped with methane-blue gelato, crushed diamond sprinkles, and a swirl of berry compote. Yes, it rains diamonds here. Yes, we put them on pizza.');

INSERT INTO pizzas (id, planet_id, name, description)
VALUES (28, 7, 'The Sideways Slice', 'This pizza is served tilted at 98 degrees, just like the planet''s axis. Sticky mozzarella, caramelized bacon, and maple glaze keep everything in place. Gravity is merely a suggestion.');

INSERT INTO pizzas (id, planet_id, name, description)
VALUES (29, 7, 'Miranda''s Mixed-Up', 'Inspired by Uranus''s geologically chaotic moon. Half Hawaiian, half meat lovers, with a surprise middle ring of dessert pizza. Makes no sense. Tastes incredible. Just like Miranda.');

INSERT INTO pizzas (id, planet_id, name, description)
VALUES (30, 7, 'Polar Vortex Pesto', 'Vibrant green pesto, fresh mozzarella, pine nuts, and cherry tomatoes on a flash-frozen then fire-baked crust. The temperature whiplash creates a texture scientists can''t explain.');

-- ==========================================
-- NEPTUNE (planet_id = 8)
-- ==========================================
INSERT INTO pizzas (id, planet_id, name, description)
VALUES (31, 8, 'Supersonic Supreme', 'Kneaded by 2,100 km/h winds for the chewiest, most elastic dough in the solar system. Topped with everything — because if you''ve traveled this far, you deserve it all.');

INSERT INTO pizzas (id, planet_id, name, description)
VALUES (32, 8, 'Deep Blue Seafood', 'The deepest blue ambiance meets the deepest ocean flavors. King crab, lobster, scallops, and a saffron cream base. The most romantic pizza in the outer solar system. Candles included.');

INSERT INTO pizzas (id, planet_id, name, description)
VALUES (33, 8, 'Triton Frost Bite', 'A cold pizza — on purpose. Frozen prosciutto shavings, chilled burrata, and a frozen basil oil crumble on an ice-cold crust. Inspired by Triton, the coldest known object in the solar system.');

INSERT INTO pizzas (id, planet_id, name, description)
VALUES (34, 8, 'Last Stop Loaded', 'You''ve reached the edge of the solar system. This pizza is loaded with every premium topping we have — truffle, wagyu beef, gold leaf, and aged parmesan. No regrets at the last stop.');

INSERT INTO pizzas (id, planet_id, name, description)
VALUES (35, 8, 'Dark Spot Chocolate', 'A dessert pizza honoring Neptune''s Great Dark Spot. Dark chocolate ganache, toasted marshmallows, crushed hazelnuts, and sea salt on a cocoa crust. The perfect sweet ending to your cosmic journey.');
