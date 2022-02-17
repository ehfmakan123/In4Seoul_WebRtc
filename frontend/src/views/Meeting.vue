<template>
	<div id="meeting-container" class="px-4 py-2 bg-gray-2">
		<div id="meeting-inner-container" class="container mw-100 bg-white py-3 px-4">
			<div class="row">
				<div id="session-video" v-if="session" class="col-9">
					<div id="session-video-header">
						<h1 v-if="!isStaff" id="session-video-title" class="fs-2 fw-bold t-blue-4">궁금한 것을 물어보세요 <span class="fs-4 fw-normal t-blue-2">Online Infromation Service</span></h1>
						<h1 v-if="isStaff" id="session-video-title" class="fs-2 fw-bold"><span class="fs-3">상담사</span> {{ myUserName }}</h1>
					</div>
					<!-- <div id="main-video" class="col-6">
						<user-video :stream-manager="mainStreamManager"/>
					</div> -->
					<div id="session-video-body" class="container mw-100 p-0">
						<div class="row">
						<div id="video-container" class="container col-2 d-flex justify-content-center align-items-center min-vh-50" style="height: 70vh;">
							<div class="col">
								<user-video :stream-manager="publisher" @click="updateMainVideoStreamManager(publisher)" class="col-6"/>
								<user-video v-for="sub in subscribers" :key="sub.stream.connection.connectionId" :stream-manager="sub" @click="updateMainVideoStreamManager(sub)" class="col-6"/>
							</div>
						</div>
						<div id="screen-container" class="col-10 text-center">
						</div>
						<div id="button-container" class="text-center mb-4 fixed-bottom">
							<!-- <input class="btn btn-large btn-danger" type="button" id="buttonLeaveSession" @click="leaveSession" value="Exit"> -->
							<button v-if="isStaff" class="btn btn-lg btn-outline-primary" @click="onoffVideo()">웹캠on/off</button>
							<button v-if="isStaff" class="btn btn-lg btn-outline-primary" @click="onoffScreen()">화면on/off</button>
							<button v-if="isStaff && !screenShared" class="btn btn-lg btn-success" @click="shareScreen()">화면공유!</button>
							<button v-if="isStaff" class="btn btn-lg btn-outline-danger my-button" @click="leaveSession" style="width: 150px">Exit</button>
							<button v-if="!isStaff" class="btn btn-lg btn-outline-danger my-button" @click="leaveSession" style="width: 150px; margin-right:100px;">Exit</button>
						</div>
					</div>
					</div>
				</div>
				<div id="session-chat" class="col-3">
					<div id="session-chat-container" class="bg-gray-1 py-1 px-2 shadow">
						<div id="chat-header" class="mt-3">
							<p class="ms-2 fw-bold">Chat</p>
							<hr style="height:2px">
						</div>
						<div id="chat-body" class="d-flex flex-column">
							<div id="chat-show">
								<chat v-for="(chat, index) in totalChats" :key="index" :chat="chat"/>
							</div>
							<!-- <div id="chat-input">
								<input type="text" v-model="myChat" @keypress.enter="enterChat(myChat)">
							</div> -->
						</div>
						<div class="chat-input input-group mt-3">
							<input type="text" class="form-control" placeholder="Type your message" @keypress.enter="enterChat(myChat)" v-model="myChat">
							<button class="btn btn-outline-secondary" type="button" @click="enterChat(myChat)"><i class="bi bi-send"></i></button>
						</div>						
					</div>
				</div>
			</div>
		</div>
	</div>
		<!-- <div id="join">
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
</template>



<script>
import axios from 'axios';
import { useRouter } from 'vue-router'
import { OpenVidu } from 'openvidu-browser';
import UserVideo from '@/components/meeting/UserVideo';
import Chat from '@/components/meeting/Chat'

axios.defaults.headers.post['Content-Type'] = 'application/json';

const SERVER_HOST = process.env.VUE_APP_SERVER_HOST
const OPENVIDU_SERVER_URL = process.env.VUE_APP_OPENVIDU_SERVER_URL
const OPENVIDU_SERVER_SECRET = process.env.VUE_APP_OPENVIDU_SERVER_SECRET

export default {
	name: 'Meeting',

	components: {
		UserVideo,
		Chat,
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
			screenShared: false,
			publisherScreen: undefined,

			// ovToken 추가
			ovToken: undefined,

			// chating 
			myChat: '',
			totalChats: [],

			// Webcam & Sound
			isWebcamOn: true,
			isScreenOn: false,
			isSoundOn: true,

			// Staff / Desk 구분
			isStaff: undefined
		}
	},
	beforeCreate () {
		const router = useRouter()
		if(!(localStorage.getItem('deskData')||localStorage.getItem('staffData'))){
			router.push({ name: 'Auth' })
		}   
	},
	created () {
		// --- ovSessionId, ovToken 갱신 ---
		// console.log(localStorage.getItem('ovSessionId'))
		this.mySessionId = localStorage.getItem('ovSessionId')
		this.ovToken = localStorage.getItem('ovToken')
		console.log('ovToken: ', this.ovToken)
		
		const staffData = JSON.parse(localStorage.getItem('staffData'))
		const deskData = JSON.parse(localStorage.getItem('deskData'))
		console.log('테스트:', deskData)
		console.log('테스트:', staffData)

		if (staffData) {
			this.myUserName = staffData.name
			this.isStaff = true
		}
		
		if (deskData) {
			this.myUserName = deskData.deskKorName
			this.isStaff = false
		}
		
		this.joinSession()

	},

	methods: {
		onoffSound () {
			this.isSoundOn = !this.isSoundOn
			if(this.isSoundOn) {
				this.publisher.publishAudio(true)
			}else {
				this.publisher.publishAudio(false)
			}
		},
		onoffScreen () {
			this.isScreenOn = !this.isScreenOn
			if(this.isScreenOn) {
				this.publisherScreen.publishVideo(true)
			}else {
				this.publisherScreen.publishVideo(false)
			}
		},
		onoffVideo () {
			this.isWebcamOn = !this.isWebcamOn
			if(this.isWebcamOn) {
				this.publisher.publishVideo(true)
			}else {
				this.publisher.publishVideo(false)
			}
		},
		// muteMyVideo () {
		// 	this.publisher.publishVideo(false)
		// },
		// unmuteMyVideo () {
		// 	this.publisher.publishVideo(true)
		// },
		shareScreen () {
			let publisher = this.OVScreen.initPublisher("screen-container", {
				videoSource: "screen", 
				resolution: "1280x780"
				// resolution: "800x400"
				});

			this.publisherScreen = publisher

			publisher.once('accessAllowed', (event) => {
				console.log(event)
				this.isScreenOn = true
				this.screenShared = true
					publisher.stream.getMediaStream().getVideoTracks()[0].addEventListener('ended', () => {
							console.log('User pressed the "Stop sharing" button');
					});
					try {
							publisher.stream.getMediaStream().getVideoTracks()[0].applyConstraints({
									// width: 1280,
									// height: 780
									width: 1021,
									heigt: 574,
							});
					} catch (error) {
							console.error('Error applying constraints: ', error);
					}								
				this.sessionScreen.publish(publisher);

			});

			publisher.once('accessDenied', (event) => {
					console.log(event)
					console.warn('ScreenShare: Access Denied');
			});
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
			console.log("채팅테스트:1 ", message !== '')
			
			const data = { message: message, nickname: this.myUserName } 

			if (localStorage.getItem('deskData') && message !== '') {
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

			if (this.$store.state.isStaff && message !== '') {
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
							console.log('[채팅]', data.message); // Message
							this.totalChats.push(data)
							console.log("totalChats: ", this.totalChats)
							// console.log('[채팅][보낸사람]', event.from); // Connection object of the sender
							// console.log('[채팅][타입]', event.type); // The type of message ("my-chat")

							// 스크롤 내리기
							let objDiv = document.getElementById("chat-body")
							objDiv.scrollTop = objDiv.scrollHeight + 100
					});


			// --- Connect to the session with a valid user token ---

			// 내가 작성한 webcam session 연결 -- getToken 제외하고 화상상담 연결
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

					}).catch((error => {
							console.warn('There was an error connecting to the session:', error.code, error.message);

					}));
			});	

			window.addEventListener('beforeunload', this.leaveSession)
		},

		leaveSession () {
			console.log('테스트: leaveSession',)
			// --- Leave the session by calling 'disconnect' method over the Session object ---
			if (this.session) this.session.disconnect();
			if (this.sessionScreen) this.sessionScreen.disconnect();

			this.session = undefined;
			this.mainStreamManager = undefined;
			this.publisher = undefined;
			this.subscribers = [];
			this.OV = undefined;

			this.OVScreen = undefined,
			this.publisherScreen = undefined,
			this.sessionScreen = undefined	

			window.removeEventListener('beforeunload', this.leaveSession);
			console.log("Desk Meeting방 나가기 버튼 클릭됨!!")

			console.log("Store isStaff 확인: ", this.$store.state.isStaff)
			if (localStorage.getItem('staffData')) {
				this.$router.push({ name: 'StaffHome' })
				axios({
					method: 'post',
					url: `${SERVER_HOST}/staff/meeting/end`,
					headers : {
						Authorization: `Bearer ${localStorage.getItem('token')}` 
					},
					data : {
						sessionId: localStorage.getItem('ovSessionId'),
						meetingLogId: localStorage.getItem('meetingLogId'),
						content: '화이팅'
					}
				})
				.then((res) => {
					console.log('Staff meeting end', res)
				})
				.catch((err) => console.log(err))
			}

			if (localStorage.getItem('deskData')) {
				this.$router.push({ name: 'DeskHome' })
				axios({
					method: 'delete',
					url: `${SERVER_HOST}/desk/meeting/end`,
					headers : {
						Authorization: `Bearer ${localStorage.getItem('token')}` 
					},
				})
				.then((res) => {
					console.log('Desk meeting end', res)
				})
				.catch((err) => console.log(err))
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
#meeting-container {
	height: 100vh;
}

#meeting-inner-container {
	height: 100%;
	width: 100%;
	border-radius: 20px;
	/* max-width: 100% !important; */
	margin: 0%;
}

#session-video-title {
	font-size: 3rem;
}

#session-video {
	height: 100%;
}

#session-video-header {
	height: 5%;
}

#session-video-body {
	height: 70%;
	margin-top: 5%;
}

#video-container video {
	/* position: relative; */
	/* float: left; */
	width: 100%;
	cursor: pointer;
}

/* 
#video-container video + div {
	float: left;
	width: 50%;
	position: relative;
	margin-top: 75%;
	margin-left: -56%;
} */

/* #video-container p {
	display: inline-block;
	background: #f8f8f8;
	padding-left: 5px;
	padding-right: 5px;
	color: #777777;
	font-weight: bold;
	border-bottom-right-radius: 4px;
} */

/* #screen-container video {
	width: 90%;
} */

#screen-container video {
	border-style: solid;
	border-width: 2px;
	/* border-color: #6B6B6B; */
	border-color: #007DC3;
	border-radius: 5px;
	box-shadow: 0 .5rem 1rem rgba(0,0,0,.15)!important;
}

#session-chat {
	padding: 0px;
}

#session-chat-container {
	height: 90vh;
	border-radius: 10px;
}

#chat-header {
	height: 5%;
}

#chat-body {
	height: 85%;
	overflow-y: scroll;
}

#chat-show {
	height: 100%;
}

/* #chat-show div:not(:first-of-type) {
	margin-top: 1rem;
} */

video {
	width: 100%;
	height: auto;
}

#button-container button {
	border-radius: 30px;
	margin-right: 3%
}

/* #main-video p {
	position: absolute;
	display: inline-block;
	background: #f8f8f8;
	padding-left: 5px;
	padding-right: 5px;
	font-size: 22px;
	color: #777777;
	font-weight: bold;
	border-bottom-right-radius: 4px;
} */
</style>