<template>
    <Page>
      <StackLayout>
        <TextField v-model="name" hint="Nombre del empleado"/>
        <Button text="Guardar" @tap="guardarEmpleado"/>
      </StackLayout>
    </Page>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        name: ''
      };
    },
    methods: {
      async guardarEmpleado() {
        try {
          const response = await axios.post('https://tu-api-rest-quarkus.com/empleados', {
            name: this.name
          });
          if (response.data.success) {
            alert('Empleado guardado');
          }
        } catch (error) {
          console.error(error);
        }
      }
    }
  };
  </script>
  