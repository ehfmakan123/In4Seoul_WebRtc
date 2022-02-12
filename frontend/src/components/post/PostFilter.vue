<template>
  <div>
    <div class="dropdown">
      <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">
        지역
      </a>

      <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
        <li
          v-for="area in areaList"
          :key="area.id"
        >
          <a class="dropdown-item">{{ area.korName }}</a>
        </li>
      </ul>
    </div>
    <div class="dropdown">
      <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">
        데스크
      </a>

      <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
        <li><a class="dropdown-item" href="#">Action</a></li>
        <li><a class="dropdown-item" href="#">Another action</a></li>
        <li><a class="dropdown-item" href="#">Something else here</a></li>
      </ul>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src

export default {
  name: 'PostFilter',
  components: {
  },
  props: ['areaList', 'deskList']
}
</script>
