
# CLINICA ODONTOLOGICA API

Es una API creada con SPRINGBOOT para el proyecto integrador de Backend 1.










## ENDPOINTS

### ODONTOLOGOS
#### Obtener lista de los Odontologos
```http
  GET /odontologos
```
#### Obtener Odontologo por ID

```http
  GET /odontologos/${id}
```

#### Guardar Odontologo en la DB
```http
  POST /odontologos
```
| Body | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `numero_De_Licencia`      | `String` | **Required**. |
| `nombre`      | `String` | **Required**.  |
| `apellido`      | `String` | **Required**.|

#### Editar Odontologo
```http
  PUT /odontologos/editar
```
| Body | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `Integer` | **Required**. |
| `numero_De_Licencia`      | `String` | **Required**. |
| `nombre`      | `String` | **Required**.  |
| `apellido`      | `String` | **Required**.|

#### Eliminar Odontologo de la DB
```http
  DELETE /odontologos/${id}
```

#### Obtener Lista de numeros de licencia similares de Odontologos
```http
  GET /odontologos/numeroLicencia?numeroLicencia=${numero_De_Licencia}
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `numero_De_Licencia`      | `String` | **Required**. |

### PACIENTES

#### Obtener lista de los Pacientes
```http
  GET /pacientes
```
#### Obtener Pacientes por ID

```http
  GET /pacientes/${id}
```

#### Guardar Pacientes en la DB
```http
  POST /pacientes
```
| Body | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `apellido`      | `String` | **Required**. |
| `nombre`      | `String` | **Required**.  |
| `dni`      | `String` | **Required**.|
| `domicilio`      | `Object` | **Required**. dentro de este objeto de domicilio les siguen los siguientes parametros|
| `calle`      | `String` | **Required**.|
| `numero`      | `Integer` | **Required**.|
| `localidad`      | `String` | **Required**.|
| `provincia`      | `String` | **Required**.|

#### Editar Pacientes
```http
  PUT /pacientes/editar
```
| Body | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `Integer` | **Required**. |
| `apellido`      | `String` | **Required**. |
| `nombre`      | `String` | **Required**.  |
| `dni`      | `String` | **Required**.|
| `domicilio`      | `Object` | **Required**. dentro de este objeto de domicilio les siguen los siguientes parametros|
| `calle`      | `String` | **Required**.|
| `numero`      | `Integer` | **Required**.|
| `localidad`      | `String` | **Required**.|
| `provincia`      | `String` | **Required**.|

#### Eliminar Pacientes de la DB
```http
  DELETE /pacientes/${id}
```

### TURNOS
#### Obtener todos los Turnos
```http
  GET /turnos/
```

#### Guardar Turno en la DB
```http
  POST /turnos/
```
| Body | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `paciente_id`      | `Integer` | **Required**. |
| `odontologo_id`      | `Integer` | **Required**. |
| `fecha`      | `String` | **Required**. |

#### Obtener turno por Id
```http
  GET /turnos/${id}
```
#### Editar turno
```http
  PUT /turnos/
```

| Body | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `Integer` | **Required**. |
| `paciente_id`      | `Integer` | **Required**. |
| `odontologo_id`      | `Integer` | **Required**. |
| `fecha`      | `String` | **Required**. |

#### Eliminar turno
```http
  DELETE /turnos/${id}
```


#### Filtrar por fechas los turnos
```http
  DELETE /turnos/fechas?fechaInit=${fechaInit}&fechaLimit=${fechaLimit}
```
| Body | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `fechaInit`      | `Date` | **Required**. |
| `fechaLimit`      | `Date` | **Required**. |

## FrontEnd

- [Repositorio](https://github.com/MarcoSavarin0/Front_Proyecto_Backend_1)

