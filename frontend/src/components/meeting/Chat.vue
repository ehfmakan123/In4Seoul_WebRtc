<template>
  <div>
    <div v-if="myUserName != chat.nickname" class="text-start">
      <div class="bg-white  chat-box text-start d-inline-block mt-2 p-2">
        <p class="">{{ chat.message }}</p>
      </div>
    </div>
    <div v-if="myUserName === chat.nickname" class="text-end">
      <div class="bg-blue-3  chat-box text-end d-inline-block mt-2 p-2">
        <p class="">{{ chat.message }}</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Chat',
  data () {
    return {
      myUserName: undefined,
    }
  },
  props: {
    chat: Object,
  },
  created () {
		const staffData = JSON.parse(localStorage.getItem('staffData'))
		const deskData = JSON.parse(localStorage.getItem('deskData'))

		if (staffData) {
			this.myUserName = staffData.name
    }
		
		if (deskData) {
			this.myUserName = deskData.deskKorName
		}

  }
}
</script>

<style>
.chat-box {
  max-width: 50%;
  border-radius: 5px;
}
</style>