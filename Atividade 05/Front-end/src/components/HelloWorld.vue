<template>
  <div class="hello">
    <Users></Users>
  </div>
</template>

<script>
import Users from '../views/Users.vue'

export default {
  name: 'HelloWord',
  props: {
    msg: String
  },
  components:{
    Users,
  },
  methods: {
    clique() {
    this.$router.push({ name: "Users", params: { name: "Nome",email:"Email"}
    }).catch(()=>{});
    },
 }
}
</script>

<style scoped>

h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
