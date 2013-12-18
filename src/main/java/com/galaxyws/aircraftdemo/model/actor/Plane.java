package com.galaxyws.aircraftdemo.model.actor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.galaxyws.aircraftdemo.model.BulletType;
import com.galaxyws.aircraftdemo.model.JetType;
import com.galaxyws.aircraftdemo.model.PlaneType;
import com.galaxyws.aircraftdemo.model.Point;
import com.galaxyws.aircraftdemo.model.instruction.ActivateDragInstruction;
import com.galaxyws.aircraftdemo.model.instruction.DeactivateDragInstruction;
import com.galaxyws.aircraftdemo.model.instruction.Instruction;
import com.galaxyws.aircraftdemo.model.instruction.MoveInstruction;
import com.galaxyws.aircraftdemo.util.CollectionUtil;

public class Plane extends Actor implements Controllable {

	private static final long serialVersionUID = 5176602335439345887L;

	private BulletType currentBulletType;
	private long lastFireTime;
	private Map<BulletType, Integer> specialBulletType = new HashMap<BulletType, Integer>();

	private boolean underDrag = false;

	public Plane() {
		this(JetType.getInstance());
	}

	public Plane(PlaneType planeType) {
		this.setType(planeType);
		this.setCurrentBulletType(planeType.getPreLoadBulletType().get(0));
		this.setLastFireTime(this.getCreateTime());
	}

	public BulletType getCurrentBulletType() {
		return currentBulletType;
	}

	private void setCurrentBulletType(BulletType currentBulletType) {
		this.currentBulletType = currentBulletType;
	}

	private List<Bullet> fixFiredBulletPosition(List<Bullet> firedBulletList) {
		if (CollectionUtil.isEmpty(firedBulletList)) {
			return null;
		}
		for (Bullet bullet : firedBulletList) {
			bullet.getShape().getPosition()
					.setX(this.getShape().getPosition().getX());
			bullet.getShape().getPosition()
					.setY(this.getShape().getPosition().getY());
			bullet.setOriginalPos(new Point(this.getShape().getPosition()));
		}
		return firedBulletList;
	}

	public List<Bullet> fire(long currentTime) {
		int fireChuck = (int) (currentTime - this.getLastFireTime());
		if (fireChuck > ((PlaneType) this.getType()).getFireInterval()) {
			this.setLastFireTime(currentTime);
			return this.fixFiredBulletPosition(currentBulletType
					.produceBullet());
		}
		return null;

	}

	public List<Bullet> fire(BulletType specialBulletType) {
		Integer count = this.specialBulletType.get(specialBulletType);
		if (null == count || count <= 0) {
			return null;
		}
		count--;
		if (count <= 0) {
			this.specialBulletType.remove(specialBulletType);
		}
		return this.fixFiredBulletPosition(specialBulletType.produceBullet());
	}

	public boolean changeBulletType(BulletType bulletType) {
		if (CollectionUtil.isEmpty(((PlaneType) this.getType())
				.getPreLoadBulletType())) {
			return false;
		}
		if (((PlaneType) this.getType()).getPreLoadBulletType().contains(
				bulletType)) {
			this.currentBulletType = bulletType;
			return true;
		}
		return false;
	}

	@Override
	public void moveTo(Point pos, long currentTime) {

	}

	public long getLastFireTime() {
		return lastFireTime;
	}

	private void setLastFireTime(long lastFireTime) {
		this.lastFireTime = lastFireTime;
	}

	@Override
	public void consume(Instruction instruction) {
		if (instruction instanceof ActivateDragInstruction) {
			ActivateDragInstruction dragInstruction = (ActivateDragInstruction) instruction;
			Point point = dragInstruction.getTarget();

			if (this.getShape().containPoint(point)) {
				this.underDrag = true;
			}
		}
		else if (instruction instanceof DeactivateDragInstruction) {
			this.underDrag = false;
		}
		else if (instruction instanceof MoveInstruction) {
			if (this.underDrag) {
				MoveInstruction moveInstruction = (MoveInstruction) instruction;
				this.getShape().setPosition(moveInstruction.getTo());
			}
		}
	}

}
