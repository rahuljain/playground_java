package problem2;

import java.util.ArrayList;

public class Animation {
	private ArrayList<String> moves;
	private Integer chamberLength;
	private Integer speed;
	
	protected class particle {
		protected char dir;
		protected int pos;
		protected particle(char direction, int position) {
			this.dir = direction;
			this.pos = position;
		}
	}
	
	public Animation() {
		this.moves = new ArrayList<String>();
		this.chamberLength = 0;
		this.speed = 0;
	}
	
	private void initialize(Integer spd, Integer len) {
		this.moves.clear();
		this.chamberLength = len;
		this.speed = spd;
	}
	private boolean move(particle p) {
		if(p.dir == 'L') {
			p.pos -= this.speed;
		}
		else if(p.dir == 'R') {
			p.pos += this.speed;
		}
		if((p.pos < 0) || (p.pos >= this.chamberLength)) {
			return false;
		}
		return true;
	}
	private String getString (ArrayList<particle> particles) {
		char[] s = new char[this.chamberLength];
		int numParticles = particles.size();
		for(int i = 0; i < this.chamberLength; i++) {
			s[i] = '.';
		}
		for(int j = 0; j < numParticles; j++) {
			s[particles.get(j).pos] = 'X'; 
		}
		return new String(s);
	}
	
	public String[] animate(int speed, String init) {
		this.initialize(speed, init.length());
		ArrayList<particle> particles = new ArrayList<particle>();
		for(int i = 0; i < this.chamberLength; i++) {
			if(init.charAt(i)!='.') {
				particles.add(new particle(init.charAt(i), i));
			}
		}
		
		while(particles.size() > 0) {
			this.moves.add(this.getString(particles));
			for(int i = 0; i < particles.size(); i++) {
				if(!move(particles.get(i))) {
					particles.remove(i);
					--i;
				}
			}
		}
		String noneLeft = new String();
		for(int i = 0; i < this.chamberLength; i++) {
			noneLeft = noneLeft.concat(".");
		}
		this.moves.add(noneLeft);
		String[] m = new String[moves.size()];
		return moves.toArray(m);		
	}
}
