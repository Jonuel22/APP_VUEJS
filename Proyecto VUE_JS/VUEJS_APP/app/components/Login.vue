<template>
  <Page>
    <StackLayout>
      <TextField v-model="username" hint="Username"/>
      <TextField v-model="password" hint="Password" secure="true"/>
      <Button text="Login" @tap="login"/>
    </StackLayout>
  </Page>
</template>

<script>
import axios from 'axios';
import { mapActions } from 'vuex';

export default {
  data() {
    return {
      username: '',
      password: ''
    };
  },
  methods: {
    ...mapActions(['login']),
    async login() {
      try {
        const response = await axios.post('https://tu-api-rest-quarkus.com/login', {
          username: this.username,
          password: this.password
        });
        if (response.data.success) {
          this.login(response.data.user);
          this.$navigateTo(HomePage);
        } else {
          alert('Credenciales inválidas');
        }
      } catch (error) {
        console.error(error);
      }
    }
  }
};
</script>
