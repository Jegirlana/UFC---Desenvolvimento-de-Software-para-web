<template>
<div id="app">
  <div class="users">
    <h2>Desenvolvimento de Software para Web - Atividade 05</h2><br>
    <form action="">
      <br>
    <h3>Cadastrar funcionário no sistema</h3><br><br>
      <div id="campo0">
      Id:
     <input type="number" v-model="id"> <br><br> 
      </div>

     <div id="campo1">
      Nome:
     <input type="text" v-model="nome"> <br><br>
     </div>

     <div id="campo2">
      Cargo:
     <input type="text" v-model="cargo"> <br><br>
     </div>

     <div id="campo3">
      Data de nascimento:
     <input type="date" v-model="datanascimento"> <br><br>
     </div>

      <div id="campo4">
      Data de entrada na empresa:
      <input type="date" v-model="dataentrada"> <br><br>
      </div>

      <div id="campo5">
      Quantidade:
      <input type="number" v-model="quantidade"> <br><br>
      </div>
    </form>

    <button @click="postUser">Post</button><!--Inserir-->
    <button @click="fetchUsers">Get All</button><!--Listar todos-->
    <button @click="fetchUserById">Get By Id</button><!--Pesquisar pelo Id-->
    <button @click="deleteUserById">Delete</button><!--Remover funcionário-->
    <button @click="putUser">Put</button><!--Realizar UPDATE-->

    <button @click="fetchUserByCargo">Filtrar por cargo</button><!--Listar por cargo-->
    <button @click="fetchUserByQuantidade">Filtrar por quantidade</button><br><br><!--Listar por quantidade-->

     
    <h3>Listagem de todos os funcionários cadastrados | Filtragem por cargo | Filtragem por quantidade</h3>
    <ul>

      <li v-for="user in users" :key="user.id">
        
        <p><strong>Id: </strong>{{ user.id }}</p>
        <p><strong>Nome: </strong>{{ user.nome }}</p>
        <p><strong>Cargo: </strong>{{ user.cargo }}</p>
        <p><strong>Data de nascimento: </strong>{{ user.datanascimento }}</p>
        <p><strong>Data de entrada na empresa: </strong>{{ user.dataentrada }}</p>
      </li>
    </ul>

  <h3>Listagem por Id | Funcionários Inseridos | Atualizados</h3>
  <ul>
   <li>    
        <p><strong>Id: </strong>{{ user.id }}</p>
        <p><strong>Nome: </strong>{{ user.nome }}</p>
        <p><strong>Cargo: </strong>{{ user.cargo }}</p>
        <p><strong>Data de nascimento: </strong>{{ user.datanascimento }}</p>
        <p><strong>Data de entrada na empresa: </strong>{{ user.dataentrada }}</p>
      </li>
    </ul>
  </div>
</div>
</template>

<script>
export default {
  name: "Users",
  data() {
    return {
      id: 0,
      nome: "",
      cargo: "",
      datanascimento: "",
      dataentrada: "",
      quantidade:"",
      user: {},
      users: [],
      baseURI: "http://localhost:8080/api/users",
      baseUploadURI: "http://localhost:8080/api/upload",
    };
  },
  methods: {
    fetchUsers: function() {
      this.$http.get(this.baseURI).then((result) => {
        this.users = result.data;
      });
    },
    fetchUserByQuantidade: function() {
      this.$http
        .get(this.baseURI + "/page?quantidade=" + this.quantidade)
        .then((result) => {
          this.users = result.data;
        })
        .catch(function(error) {
          console.log(error);
        });
    },
    fetchUserByCargo: function() {
      this.$http
        .get(this.baseURI + "/search?cargo=" + this.cargo)
        .then((result) => {
          this.users = result.data;
          console.log(result.data)
        })
        .catch(function(error) {
          console.log(error);
        });
    },
    fetchUserById: function() {
      this.$http
        .get(this.baseURI + "/" + this.id)
        .then((result) => {
          this.user = result.data;
        })
        .catch(function(error) {
          console.log(error);
        });
    },
    postUser: function() {
      this.$http
        .post(this.baseURI, {
          nome: this.nome,
          cargo: this.cargo,
          datanascimento: this.datanascimento,
          dataentrada: this.dataentrada,
        })
        .then((result) => {
          console.log(result);
          this.user = result.data;
        });
    },
      putUser: function() {
      let obj = {
        nome: this.nome,
        cargo: this.cargo,
        datanascimento: this.datanascimento,
        dataentrada: this.dataentrada,
      };
 
      this.$http.put(this.baseURI + "/" + this.id, obj).then((result) => {
        console.log(result);
        this.user = result.data;
      });
    },
    deleteUserById: function() {
      this.$http.delete(this.baseURI + "/" + this.id).then((result) => {
        console.log(result.status);
      });
    },
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #f1efef;
  
}

#campo4 input{
  margin-left: 43px;
  border-radius: 4px;
  width: 200px;
  height: 25px;
}
#campo3 input{
  margin-left: 100px;
  border-radius: 4px;
  width: 200px;
  height: 25px;
}
#campo2 input{
  margin-left: 195px;
  border-radius: 4px;
  width: 200px;
  height: 25px;
}
#campo1 input{
  margin-left: 195px;
  border-radius: 4px;
  width: 200px;
  height: 25px;
}
#campo0 input{
  margin-left: 220px;
  border-radius: 4px;
  width: 200px;
  height: 25px;
}
#campo5 input{
  margin-left: 160px;
  border-radius: 4px;
  width: 200px;
  height: 25px;
}
button{
  margin:5px;
}
ul{
  background: teal;
  margin: 0px 500px 0 500px;
}
form{
  background: teal;
  margin: 0px 450px 50px 450px;
}
h2,
h3{
  color: #2c3e50;
}
#user,
#listagem{
  color: #2c3e50;
}

</style>
