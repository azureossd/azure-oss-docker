<template>
  <b-card no-body class="overflow-hidden mb-3 shadow">
    <b-row no-gutters>
      <b-col md="2">
        <b-card-img
          src="https://i.pravatar.cc/400"
          alt="Image"
          class="rounded-circle p-4"
        />
      </b-col>
      <b-col md="10">
        <b-card-body>
          <b-card-title>
            <NuxtLink to="#">{{ name }}</NuxtLink>
          </b-card-title>
          <b-card-sub-title>{{ role }}</b-card-sub-title>
          <UserRating />
          <b-card-text style="font-style: italic">
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. In feugiat metus enim, quis finibus magna ornare sit amet. Vestibulum vitae elementum odio, non accumsan dui. Integer vel erat ipsum. Sed eu tincidunt est, sit amet rutrum nulla.
          </b-card-text>
        </b-card-body>
      </b-col>
    </b-row>
  </b-card>
</template>

<script>
export default{
   props: {
    name: {
      type: String,
      required: false,
    },
    role: {
      type: String,
      required: false,
    },
    rating: {
      type: Number,
      required: false,
    }
  }
}
</script>
