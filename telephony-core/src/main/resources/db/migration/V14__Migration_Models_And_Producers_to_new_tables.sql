CREATE OR REPLACE FUNCTION migrate_models_and_producers() RETURNS void AS $$
DECLARE
    producer_label varchar(100);
    model_label varchar(100);
    producer_id bigint;
    model_id bigint;
    
    row_product products%ROWTYPE;
    row_producer producers%ROWTYPE;
    row_model models%ROWTYPE;
BEGIN
	
	FOR row_product IN SELECT * FROM products
	LOOP
		producer_label := row_product.producer;
		model_label := row_product.model;
		
		SELECT * INTO row_producer FROM producers p WHERE p.label = producer_label;
		SELECT * INTO row_model FROM models m WHERE m.label = model_label;
		
		IF 
			row_producer.id IS NOT NULL AND 
			row_model.id IS NOT NULL AND 
			row_model.producer_id = row_producer.id 
		THEN
		-- jesli znaleziono producer i model i są powiazane to powiazac do produktu
			
			UPDATE products SET model_id = row_model.id WHERE id = row_product.id; 
		
		ELSIF 
			row_producer.id IS NOT NULL AND 
			row_model.id IS NOT NULL AND 
			row_model.producer_id <> row_producer.id 
		THEN
		-- jesli znaleziono producer i model ale nie sa powiazane ze soba i powiazac do produktu
			
			UPDATE models SET producer_id = row_producer.id WHERE id = row_model.id;
			UPDATE products SET model_id = row_model.id WHERE id = row_product.id; 
		
		ELSIF 
			row_model.id IS NULL AND row_producer.id IS NULL
		THEN
		-- jesli nie znaleziono producer , dodac producer, dodac model, powiazac, powiazac z produktem
			
			INSERT INTO producers (id, label) VALUES ( nextval('producers_seq'), producer_label);
			INSERT INTO models (id, producer_id, label) VALUES ( nextval('models_seq'), lastval(), model_label );
			
			UPDATE products SET model_id = lastval() WHERE id = row_product.id;
		ELSIF 
			row_model.id IS NULL AND row_producer.id IS NOT NULL
		THEN			
		-- jesli nie znaleziono model, dodac producer, dodac model, powiazac, powiazac z produktem
			
			INSERT INTO models (id, producer_id, label) VALUES ( nextval('models_seq'), row_producer.id, model_label );
			
			UPDATE products SET model_id = lastval() WHERE id = row_product.id;
			
		END IF;
		
	END LOOP;	
    
END;
$$ LANGUAGE plpgsql;

SELECT migrate_models_and_producers();