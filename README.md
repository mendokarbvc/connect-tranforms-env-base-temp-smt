# tranforms-env-base-temp
Arquetipo de Transformación Kafka Connect SMT

Este arquetipo proporciona una estructura base para desarrollar transformaciones personalizadas utilizando Kafka Connect SMT. Las transformaciones de un solo mensaje (SMT) son utilizadas para modificar y enriquecer los registros individuales que fluyen a través de Kafka Connect.

Descripción

Este arquetipo implementa una transformación genérica que puede ser extendida para aplicar diferentes lógicas de transformación sobre los registros de conexión (ConnectRecord). Utiliza la API de Kafka Connect para gestionar la configuración y la aplicación de la transformación.

Funcionalidades

Configuración Personalizada: Define parámetros de configuración a través de ConfigDef para adaptar el comportamiento de la transformación.
Aplicación de Transformación: Implementa la interfaz Transformation para aplicar lógicas específicas sobre los registros de conexión.
Limpieza de Recursos: Implementa el método close para manejar la liberación de recursos al finalizar el uso de la transformación.
Uso

Configuración:

Define los parámetros de configuración requeridos en ConfigDef, incluyendo descripciones y valores por defecto.
Implementación de Transformación:

Extiende la clase MyTransformation y provee la lógica específica de transformación en el método applyTransform.
Ejemplo de Uso:

// Configuración
Map<String, String> props = new HashMap<>();
props.put("my.config.param", "value");

// Creación del transformador
MyTransformation<ConnectRecord> transformer = new MyTransformation<ConnectRecord>() {
    @Override
    protected ConnectRecord applyTransform(ConnectRecord record) {
        // Implementación específica de transformación aquí
        return record;
    }
};
transformer.configure(props);

// Aplicación de la transformación a un registro
ConnectRecord record = ...; // Obtener el registro de Kafka Connect
ConnectRecord transformedRecord = transformer.apply(record);

Ejemplo de Transformacion YAML
transforms=insertuuid
transforms.insertuuid.type=com.nuamx.temp.kafka.connect.smt.InsertUuid$Value
transforms.insertuuid.uuid.field.name="uuid"