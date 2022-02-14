<template>
	<div id="main-container" class="container">
		<!-- <div id="join" v-if="!session">
			<div id="img-div"><img src="resources/images/openvidu_grey_bg_transp_cropped.png" /></div>
			<div id="join-dialog" class="jumbotron vertical-center">
				<h1>Join a video session</h1>
				<div class="form-group">
					<p>
						<label>Participant</label>
						<input v-model="myUserName" class="form-control" type="text" required>
					</p>
					<p>
						<label>Session</label>
						<input v-model="mySessionId" class="form-control" type="text" required>
					</p>
					<p class="text-center">
						<button class="btn btn-lg btn-success" @click="joinSession()">Join!</button>
					</p>
				</div>
			</div>
		</div> -->

		<div id="session" v-if="session">
			<div id="session-header">
				<h1 id="session-title">{{ mySessionId }}</h1>
				<input class="btn btn-large btn-danger" type="button" id="buttonLeaveSession" @click="leaveSession" value="Leave session">
			</div>
			<div id="main-video" class="col-md-6">
				<user-video :stream-manager="mainStreamManager"/>
			</div>
			<div id="video-container" class="col-md-6">
				<user-video :stream-manager="publisher" @click="updateMainVideoStreamManager(publisher)"/>
				<user-video v-for="sub in subscribers" :key="sub.stream.connection.connectionId" :stream-manager="sub" @click="updateMainVideoStreamManager(sub)"/>
			</div>
			<div id="screen-container">

			</div>
			<input type="text" v-model="myChat" @keypress.enter="enterChat(myChat)">
			<button class="btn btn-lg btn-success" @click="shareScreen()">화면공유!</button>
			<button class="btn btn-lg btn-danger" @click="muteMyVideo()">내화면끄기</button>
			<button class="btn btn-lg btn-primary" @click="unmuteMyVideo()">내화면켜기</button>
		</div>
	</div>
</template>



<script>
import axios from 'axios';
import { OpenVidu } from 'openvidu-browser';
import UserVideo from '@/components/meeting/UserVideo';

axios.defaults.headers.post['Content-Type'] = 'application/json';

const OPENVIDU_SERVER_URL = "https://i6a503.p.ssafy.io:5443";
const OPENVIDU_SERVER_SECRET = "ssafy503";

export default {
	name: 'Meeting',

	components: {
		UserVideo,
	},

	data () {
		return {
			OV: undefined,
			session: undefined,
			mainStreamManager: undefined,
			publisher: undefined,
			subscribers: [],

			mySessionId: 'SessionA',
			myUserName: 'Participant' + Math.floor(Math.random() * 100),

			// Screen Sharing
			OVScreen: undefined,
			sessionScreen: undefined,
			screensharing: false,
			// publisherScreen: undefined

			// ovToken 추가
			ovToken: undefined,

			// chating 
			myChat: '',
			totalChats: [],
		}
	},

	created () {

		// --- ovSessionId, ovToken 갱신 ---
		// console.log(localStorage.getItem('ovSessionId'))
		this.mySessionId = localStorage.getItem('ovSessionId')
		this.ovToken = localStorage.getItem('ovToken')
		console.log('ovToken: ', this.ovToken)
		
		if (this.$store.state.isStaff) {
			this.myUserName = '상담사'
		}
		
		if (this.$store.state.isDesk) {
			this.myUserName = '데스크'
		}
		
		this.joinSession()
	},

	methods: {
		muteMyVideo () {
			this.publisher.publishVideo(false)
		},
		unmuteMyVideo () {
			this.publisher.publishVideo(true)
		},
		shareScreen () {
			// --- 나만 화면공유 되는 코드 ---
			// this.getToken(localStorage.getItem('ovSessionId')).then((token) => {
			// 		// console.log("테스트", token)
			// 		// console.log("테스트", this.ovToken)
			// 		this.session.connect(token).then(() => {
			// 			console.log("테스트1", )
			// 				let publisher = this.OV.initPublisher("screen-container", { videoSource: "screen" });

			// 				publisher.once('accessAllowed', (event) => {
			// 					console.log(event)
			// 						publisher.stream.getMediaStream().getVideoTracks()[0].addEventListener('ended', () => {
			// 								console.log('User pressed the "Stop sharing" button');
			// 						});
			// 						this.session.publish(publisher);

			// 				});

			// 				publisher.once('accessDenied', (event) => {
			// 					console.log(event)
			// 						console.warn('ScreenShare: Access Denied');
			// 				});

			// 		}).catch((error => {
			// 				console.warn('There was an error connecting to the session:', error.code, error.message);

			// 		}));
			// });
		
		},
		enterChat (message) {
			console.log("채팅테스트: ", message)
			
			const data = { message: message, nickname: this.myUserName } 

			if (this.$store.state.isDesk) {
				// console.log('[채팅] 데스크가 보내기 시작')
				this.session.signal({
									data: JSON.stringify(data),  // Any string (optional)
									to: [],                     // Array of Connection objects (optional. Broadcast to everyone if empty)
									type: 'desk-chat'             // The type of message (optional)
								})
								.then(() => {
										console.log('Message successfully sent');
								})
								.catch(error => {
										console.error(error);
								});	
	
				this.myChat = ''
			}

			if (this.$store.state.isStaff) {
				// console.log('[채팅] 스태프가 보내기 시작')
				this.session.signal({
									data: JSON.stringify(data),  // Any string (optional)
									to: [],                     // Array of Connection objects (optional. Broadcast to everyone if empty)
									type: 'staff-chat'             // The type of message (optional)
								})
								.then(() => {
										console.log('Message successfully sent');
								})
								.catch(error => {
										console.error(error);
								});	
	
				this.myChat = ''
			}

		},
		joinSession () {
			// --- Get an OpenVidu object ---
			this.OV = new OpenVidu();
			this.OVScreen = new OpenVidu();

			// --- Init a session ---
			this.session = this.OV.initSession();
			this.sessionScreen = this.OVScreen.initSession();


			// --- Specify the actions when events take place in the session ---

			// On every new Stream received...
			this.session.on('streamCreated', ({ stream }) => {
				if (stream.typeOfVideo == "CAMERA") {
					const subscriber = this.session.subscribe(stream);
					this.subscribers.push(subscriber);
					console.log('[subscribers][웹캠공유]', this.subscribers)
				}
			});

			this.sessionScreen.on('streamCreated', ({ stream }) => {
				if (stream.typeOfVideo == "SCREEN") {
					console.log('[화면공유] 남한테 가나?')

					// 웹캠 공유와 비슷하게 짠 코드
					// const subscriberScreen = this.sessionScreen.subscribe(stream);
					// this.subscribers.push(subscriberScreen);
					console.log('[subscribers][화면공유]', this.subscribers)

					// screen-share 튜토리얼과 비슷하게 짠 코드
					const subscriberScreen = this.sessionScreen.subscribe(stream, 'screen-container');
					console.log(subscriberScreen)
					// subscriberScreen.on('videoElementCreated', event => {
					// Add a new <p> element for the user's nickname just below its video
					// this.appendUserData(event.element, subscriberScreen.stream.connection);
					// });
				}
			});

			// On every Stream destroyed...
			this.session.on('streamDestroyed', ({ stream }) => {
				const index = this.subscribers.indexOf(stream.streamManager, 0);
				if (index >= 0) {
					this.subscribers.splice(index, 1);
				}
			});

			// On every asynchronous exception...
			this.session.on('exception', ({ exception }) => {
				console.warn(exception);
			});

			// Receiver of the message (usually before calling 'session.connect')
			this.session.on('signal', (event) => {
							const data = JSON.parse(event.data)
							console.log('[채팅]', data); // Message
							// console.log('[채팅][보낸사람]', event.from); // Connection object of the sender
							// console.log('[채팅][타입]', event.type); // The type of message ("my-chat")
					});


			// --- Connect to the session with a valid user token ---

			// 내가 추가한 코드 -- getToken 제외하고 화상상담 연결
			console.log('JoinSession에서 ovToken값: ', this.ovToken)
			this.session.connect(this.ovToken, { clientData: this.myUserName })
				.then(() => {
					// --- Get your own camera stream with the desired properties ---
					console.log("session connect: 들어오나!?")
					console.log('Spring에서 생성한 토큰: ', this.ovToken)

					let publisher = this.OV.initPublisher(undefined, {
						audioSource: undefined, // The source of audio. If undefined default microphone
						videoSource: undefined, // The source of video. If undefined default webcam
						publishAudio: true,  	// Whether you want to start publishing with your audio unmuted or not
						publishVideo: true,  	// Whether you want to start publishing with your video enabled or not
						resolution: '640x480',  // The resolution of your video
						frameRate: 30,			// The frame rate of your video
						insertMode: 'APPEND',	// How the video is inserted in the target element 'video-container'
						mirror: false       	// Whether to mirror your local video or not
					});

					console.log("session connect: mainStreamManager")
					this.mainStreamManager = publisher;
					console.log("session connect: publisher")
					this.publisher = publisher;

					// --- Publish your stream ---

					console.log("session connect: session publish")
					this.session.publish(this.publisher);

					// Sender of the message (after 'session.connect')
					// this.session.signal({
					// 					data: `My custom message ${this.myUserName}`,  // Any string (optional)
					// 					to: [],                     // Array of Connection objects (optional. Broadcast to everyone if empty)
					// 					type: 'my-chat'             // The type of message (optional)
					// 				})
					// 				.then(() => {
					// 						console.log('Message successfully sent');
					// 				})
					// 				.catch(error => {
					// 						console.error(error);
					// 				});

				})
				.catch(error => {
					console.log('There was an error connecting to the session:', error.code, error.message);
				});		


			// Screen-Share 토큰 받아서 연결
			this.getToken(this.mySessionId).then((token) => {
					// console.log("테스트", token)
					// console.log("테스트", this.ovToken)
					this.sessionScreen.connect(token).then(() => {
						console.log("테스트1", )
							let publisher = this.OVScreen.initPublisher("screen-container", { videoSource: "screen" });

							publisher.once('accessAllowed', (event) => {
								console.log(event)
									publisher.stream.getMediaStream().getVideoTracks()[0].addEventListener('ended', () => {
											console.log('User pressed the "Stop sharing" button');
									});
									this.sessionScreen.publish(publisher);

							});

							publisher.once('accessDenied', (event) => {
									console.log(event)
									console.warn('ScreenShare: Access Denied');
							});

					}).catch((error => {
							console.warn('There was an error connecting to the session:', error.code, error.message);

					}));
			});	

			window.addEventListener('beforeunload', this.leaveSession)
		},

		leaveSession () {
			// --- Leave the session by calling 'disconnect' method over the Session object ---
			if (this.session) this.session.disconnect();
			if (this.sessionScreen) this.sessionScreen.disconnect();

			this.session = undefined;
			this.mainStreamManager = undefined;
			this.publisher = undefined;
			this.subscribers = [];
			this.OV = undefined;

			window.removeEventListener('beforeunload', this.leaveSession);
			console.log("Desk Meeting방 나가기 버튼 클릭됨!!")

			console.log("Store isStaff 확인: ", this.$store.state.isStaff)
			if (this.$store.state.isStaff) {
				this.$router.push({ name: 'StaffHome' })
			}
			
			if (this.$store.state.isDesk) {
				this.$router.push({ name: 'DeskHome' })
			}
			
		},

		updateMainVideoStreamManager (stream) {
			if (this.mainStreamManager === stream) return;
			this.mainStreamManager = stream;
		},

		// Screen-share 관련 메소드
		// appendUserData(videoElement, connection) {
		// 	var userData;
		// 	var nodeId;
		// 	if (typeof connection === "string") {
		// 		userData = connection;
		// 		nodeId = connection;
		// 	} else {
		// 		userData = JSON.parse(connection.data).clientData;
		// 		nodeId = connection.connectionId;
		// 	}
		// 	var dataNode = document.createElement('div');
		// 	dataNode.className = "data-node";
		// 	dataNode.id = "data-" + nodeId;
		// 	dataNode.innerHTML = "<p>" + userData + "</p>";
		// 	videoElement.parentNode.insertBefore(dataNode, videoElement.nextSibling);
		// },

		/**
		 * --------------------------
		 * SERVER-SIDE RESPONSIBILITY
		 * --------------------------
		 * These methods retrieve the mandatory user token from OpenVidu Server.
		 * This behavior MUST BE IN YOUR SERVER-SIDE IN PRODUCTION (by using
		 * the API REST, openvidu-java-client or openvidu-node-client):
		 *   1) Initialize a Session in OpenVidu Server	(POST /openvidu/api/sessions)
		 *   2) Create a Connection in OpenVidu Server (POST /openvidu/api/sessions/<SESSION_ID>/connection)
		 *   3) The Connection.token must be consumed in Session.connect() method
		 */

		getToken (mySessionId) {
			return this.createSession(mySessionId)
			.then(sessionId => 
				this.createToken(sessionId)
			);
		},

		// See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-session
		createSession (sessionId) {
			return new Promise((resolve, reject) => {
				axios
					.post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions`, JSON.stringify({
						customSessionId: sessionId,
					}), {
						auth: {
							username: 'OPENVIDUAPP',
							password: OPENVIDU_SERVER_SECRET,
						},
					})
					.then(response => response.data)
					.then(data => {
						console.log('createSession data:', data)
						resolve(data.id)
					})
					.catch(error => {
						if (error.response.status === 409) {
							resolve(sessionId);
						} else {
							console.warn(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}`);
							if (window.confirm(`No connection to OpenVidu Server. This may be a certificate error at ${OPENVIDU_SERVER_URL}\n\nClick OK to navigate and accept it. If no certificate warning is shown, then check that your OpenVidu Server is up and running at "${OPENVIDU_SERVER_URL}"`)) {
								location.assign(`${OPENVIDU_SERVER_URL}/accept-certificate`);
							}
							reject(error.response);
						}
					});
			});
		},

		// See https://docs.openvidu.io/en/stable/reference-docs/REST-API/#post-connection
		createToken (sessionId) {
			return new Promise((resolve, reject) => {
				axios
					.post(`${OPENVIDU_SERVER_URL}/openvidu/api/sessions/${sessionId}/connection`, {}, {
						auth: {
							username: 'OPENVIDUAPP',
							password: OPENVIDU_SERVER_SECRET,
						},
					})
					.then(response => response.data)
					.then(data => {
						console.log('createToken에서 토큰값:', data.token)
						resolve(data.token)
						})
					.catch(error => reject(error.response));
			});
		},
	}
}
</script>
<style>
h1 {
	font-size: 3rem;
	font-weight: 600;
	margin-top: 2rem;
	margin-left: 2rem;
}

#video-container video {
	position: relative;
	float: left;
	width: 50%;
	cursor: pointer;
}

#video-container video + div {
	float: left;
	width: 50%;
	position: relative;
	margin-left: -50%;
}

#video-container p {
	display: inline-block;
	background: #f8f8f8;
	padding-left: 5px;
	padding-right: 5px;
	color: #777777;
	font-weight: bold;
	border-bottom-right-radius: 4px;
}

video {
	width: 100%;
	height: auto;
}

#main-video p {
	position: absolute;
	display: inline-block;
	background: #f8f8f8;
	padding-left: 5px;
	padding-right: 5px;
	font-size: 22px;
	color: #777777;
	font-weight: bold;
	border-bottom-right-radius: 4px;
}
</style>