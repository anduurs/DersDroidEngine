precision mediump float;

varying vec2 texCoord;
varying vec4 color;

uniform sampler2D sampler;

void main(){
	gl_FragColor = texture2D(sampler, texCoord.xy);
	//gl_FragColor = color;
}