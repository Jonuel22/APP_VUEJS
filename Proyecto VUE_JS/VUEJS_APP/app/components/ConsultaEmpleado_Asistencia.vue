<template>
    <div>
      <h1>Gestión de Empleados y Asistencias</h1>
      <!-- Muestra el mensaje de carga si loading está en true -->
      <div v-if="loading">Cargando datos...</div>
  
      <!-- Cuando loading es false, se muestran los datos de empleados y asistencias -->
      <div v-else>
        <h2>Empleados</h2>
        <!-- Muestra una lista de empleados obtenidos de la API -->
        <ul>
          <!-- v-for recorre el array de empleados para generar una lista -->
          <li v-for="empleado in empleados" :key="empleado.id">{{ empleado.nombre }}</li>
        </ul>
  
        <h2>Asistencias</h2>
        <!-- Muestra una lista de asistencias obtenidas de la API -->
        <ul>
          <!-- v-for recorre el array de asistencias para generar una lista -->
          <li v-for="asistencia in asistencias" :key="asistencia.id">{{ asistencia.fecha }}</li>
        </ul>
      </div>
    </div>
  </template>
  
  <script>
  // Importamos axios, que es una biblioteca para realizar solicitudes HTTP
  import axios from 'axios';
  
  export default {
    data() {
      return {
        // Inicializamos los arrays vacíos que contendrán los datos de empleados y asistencias
        empleados: [],
        asistencias: [],
        // La bandera "loading" indica si estamos esperando los datos
        loading: true,
        // "error" capturará cualquier error que ocurra durante las solicitudes a la API
        error: null,
      };
    },
    methods: {
      // Función que realiza las solicitudes a la API
      async fetchData() {
        try {
          // Promise.all permite realizar múltiples solicitudes en paralelo
          // Aquí, axios realiza dos solicitudes: una para obtener empleados y otra para asistencias.
          // Las dos solicitudes se ejecutan de manera simultánea y el proceso espera a que ambas terminen antes de continuar.
          const [empleadosResponse, asistenciasResponse] = await Promise.all([
            // Solicitud GET a la API para obtener los empleados
            axios.get('https://tu-api-quarkus.com/api/empleados'),
            // Solicitud GET a la API para obtener las asistencias
            axios.get('https://tu-api-quarkus.com/api/asistencias'),
          ]);
  
          // Cuando ambas promesas se resuelven, los datos recibidos se asignan a sus respectivas variables
          this.empleados = empleadosResponse.data;
          this.asistencias = asistenciasResponse.data;
  
          // Finalizamos el estado de carga, cambiando "loading" a false para mostrar los datos en el HTML
          this.loading = false;
        } catch (error) {
          // Si ocurre un error en cualquiera de las solicitudes, se captura aquí
          console.error('Error fetching data:', error);
          this.error = error; // Guardamos el error para posibles usos (por ejemplo, mostrar un mensaje de error en la UI)
          this.loading = false; // Incluso si hay un error, detenemos el estado de carga
        }
      }
    },
    // El método "mounted" se ejecuta cuando el componente ha sido cargado en la página
    mounted() {
      // Llamamos a la función "fetchData" para realizar las solicitudes a la API una vez que el componente está montado
      this.fetchData();
    }
  };
  </script>
  