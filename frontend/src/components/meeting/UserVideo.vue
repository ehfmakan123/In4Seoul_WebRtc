<template>
<div class="my-video shadow" v-if="streamManager">
	<ov-video :stream-manager="streamManager"/>
	<div class="d-inline-block user-name"><p>{{ clientData }}</p></div>
</div>
</template>

<script>
import OvVideo from './OvVideo';

export default {
	name: 'UserVideo',

	components: {
		OvVideo,
	},

	props: {
		streamManager: Object,
	},

	computed: {
		clientData () {
			const { clientData } = this.getConnectionData();
			return clientData;
		},
	},

	methods: {
		getConnectionData () {
			const { connection } = this.streamManager.stream;
			return JSON.parse(connection.data);
		},
	},
};
</script>

<style scoped>

.my-video {
	width: 100%;
	height: 100%;
	margin-top: 1rem;
	text-align: center;
	border-style: solid;
	border-width: 2px;
	border-color: #6B6B6B;
	border-radius: 5px;
}

.user-name {
	border-radius: 5px;
	padding: 5px;
}
</style>