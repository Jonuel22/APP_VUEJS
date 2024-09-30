<template>
  <Page>
    <StackLayout>
      <ListView for="item in items">
        <v-template>
          <Label :text="item.name"/>
        </v-template>
      </ListView>
    </StackLayout>
  </Page>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      items: []
    };
  },
  async mounted() {
    try {
      const response = await axios.get('https://tu-api-rest-quarkus.com/consulta');
      this.items = response.data;
    } catch (error) {
      console.error(error);
    }
  }
};
</script>
