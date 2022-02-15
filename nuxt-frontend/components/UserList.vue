<template>
  <b-col>
    <h1>Docker Swim Session🏊🐋</h1>
    <p v-if="$fetchState.pending">Fetching users...</p>
    <p v-else-if="$fetchState.error">An error occurred :(</p>
    <div v-if="!$fetchState.pending">
      <transition-group name="flip-list">
        <UserCard
          v-for="(user, id) in users"
          v-bind:index="id"
          v-bind:key="user.createdAt"
          v-bind:name="user.name"
          v-bind:role="user.role"
          v-bind:rating="user.rating"
          class="user-card"
        />
      </transition-group>
    </div>
    <button @click="$fetch">Refresh</button>
  </b-col>
</template>

<script>
export default {
  data() {
    return {
      users: [],
    };
  },
  created() {
    this.$nuxt.$on("add-user", (responseData) => {
      this.users.unshift(responseData);
    });
  },
  beforeDestroy() {
    this.$nuxt.$off("add-user");
  },
  async fetch() {
    await this.$axios
      .$get("/users/search/findAllByOrderByIdDesc")
      .then((response) => {
        this.users = response._embedded.users;
      })
      .catch((error) => {
        console.log(error);
      });
  },
  fetchOnServer: false,
};
</script>

<style>
.user-card {
  transition: all 1s;
}
.user-card-enter,
.user-card-leave-to {
  opacity: 0;
  transform: translateX(30px);
}
.user-card-leave-active {
  position: absolute;
}
</style>
