/*
 * Copyright 2011 Witoslaw Koczewsi <wi@koczewski.de>, Artjom Kochtchi
 * 
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public
 * License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package ilarkesto.base.time;

import ilarkesto.base.TmExtend;
import ilarkesto.core.time.Time;

public final class TimePeriodExtend extends ilarkesto.core.time.TimePeriod {

	public static final TimePeriodExtend ZERO = new TimePeriodExtend(0);

	public TimePeriodExtend(long millis) {
		super(millis);
	}

	public TimePeriodExtend(String s) {
		super(s);
	}

	@Override
	protected TimePeriodExtend newTimePeriod(long millis) {
		return new TimePeriodExtend(millis);
	}

	// ---

	@Override
	public TimePeriodExtend multiplyBy(int factor) {
		return (TimePeriodExtend) super.multiplyBy(factor);
	}

	@Override
	public TimePeriodExtend divide(int divisor) {
		return (TimePeriodExtend) super.divide(divisor);
	}

	@Override
	public TimePeriodExtend subtract(ilarkesto.core.time.TimePeriod tp) {
		return (TimePeriodExtend) super.subtract(tp);
	}

	@Override
	public TimePeriodExtend add(ilarkesto.core.time.TimePeriod tp) {
		return (TimePeriodExtend) super.add(tp);
	}

	public TimePeriodExtend subtract(Time time) {
		return new TimePeriodExtend(toMillis() - time.toMillis());
	}

        @Override
	public String toHoursAndMinutesString() {
		long hours = toHours();
		long minutes = toMinutes() - (hours * 60);
		StringBuilder sb = new StringBuilder();
		sb.append(hours);
		sb.append(':');
		if (minutes < 10) {
                        sb.append('0');
                }
		sb.append(minutes);
		return sb.toString();
	}

	public static TimePeriodExtend seconds(int seconds) {
		return new TimePeriodExtend(seconds * TmExtend.SECOND);
	}

	public static TimePeriodExtend minutes(int minutes) {
		return new TimePeriodExtend(minutes * TmExtend.MINUTE);
	}

	public static TimePeriodExtend hours(int hours) {
		return new TimePeriodExtend(hours * TmExtend.HOUR);
	}

	public static TimePeriodExtend days(int days) {
		return new TimePeriodExtend(days * TmExtend.DAY);
	}

	public static TimePeriodExtend weeks(int weeks) {
		return new TimePeriodExtend(weeks * TmExtend.WEEK);
	}

}
