<template>
  <div class="col-3 p-4">
    <div class="card shadow postit bg-yellow">
      <div class="card-body" data-bs-toggle="modal" data-bs-target="#detailModal">
        <p class="card-title"><strong>{{ post.title }}</strong></p>
        <p class="card-text">{{ post.content }}</p>
      </div>
    </div>

  </div>
</template>

<script>

export default {
  name: 'PostListItem',
  components: {
  },
  props: ["post"],
  setup() {

  }
}
</script>
<style scoped>
  strong {
    font-weight: bold;
  }

  .postit {
    height: 20vw; 
    border: 0rem
  }
</style>
