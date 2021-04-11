<template>
  <div class="container">
    <header class="jumbotron">
      <h3>
        <strong>{{currentUser.user.nome}}</strong>
      </h3>
    </header>
    <p>
      <strong>Id:</strong>
      {{currentUser.user.id}}
    </p>
    <p>
      <strong>Email:</strong>
      {{currentUser.user.email}}
    </p>
    <strong>Authorities:</strong>
      {{currentUser.user.roles}}
  </div>
</template>

<script>
export default {
  name: 'Profile',
  mounted() {
     this.$store.dispatch('auth/verificar');
  },
  computed: {
    currentUser() {
      return this.$store.state.auth.user
    }
  }
};
</script>