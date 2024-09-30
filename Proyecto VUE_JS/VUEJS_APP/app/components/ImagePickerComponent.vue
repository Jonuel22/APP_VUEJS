<template>
    <Page>
      <StackLayout>
        <Button text="Seleccionar Imagen" @tap="seleccionarImagen"/>
        <Image v-for="img in images" :src="img" />
      </StackLayout>
    </Page>
  </template>
  
  <script>
  import { ImagePicker } from '@nativescript/imagepicker';
  import { mapActions } from 'vuex';
  
  export default {
    data() {
      return {
        images: []
      };
    },
    methods: {
      ...mapActions(['saveImage']),
      async seleccionarImagen() {
        const context = ImagePicker.create({ mode: 'multiple' });
        context
          .authorize()
          .then(() => context.present())
          .then((selection) => {
            this.images = selection.map(img => img.fileUri);
            this.saveImage(this.images);
          })
          .catch((error) => {
            console.error(error);
          });
      }
    }
  };
  </script>
  