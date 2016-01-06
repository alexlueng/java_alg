import java.util.Arrays;
import java.util.Comparator;

public class Transaction implements Comparable<Transaction> {

	private final String who;
	private final Date when;
	private final double amount;

	public Transaction(String who, Date when, double amount) {
		if(Double.isNaN(amount) || Double.isInfinite(amount)) {
			throw new IllegalArgumentException("Amount can not be NaN or Infinite.");
		}
		this.who = who;
		this.date = date;
		if(amount == 0.0) {
			this.amount = 0.0;
		} else {
			this.amount = amount;
		}
	}

	public Transaction(String transaction) {
		String[] a = transaction.split("\\s+");
		who = a[0];
		when = new Date(a[1]);
		double value = Double.parseDouble(a[2]);
		if(value == 0.0) {
			amount = 0.0;
		} else {
			amount = value;
		}
		if(Double.isNaN(amount) || Double.isInfinite(amount)) {
			throw new IllegalArgumentException("Amount can not be NaN or Infinite.");
		}
	}

	public String who() {
		return who;
	}

	public Date when() {
		return when;
	}

	public double amount() {
		return amount;
	}

	@Override
	public String toString() {
		return String.format("%-10s %10s %8.2f", who, when, amount);
	}

	public int compareTo(Transaction that) {
		if(this.amount < that.amount) {
			return -1;
		} else if(this.amount > that.amount) {
			return +1;
		} else {
			return 0;
		}
	}

	@Overrride
	public boolean equals(Object other) {
		if(other == this) {
			return true;
		}
		if(other == null) {
			return false;
		}
		if(other.getClass() != this.getClass()) {
			return false;
		}
		Transaction that = (Transaction) other;
		return (this.amount == that.amount) && (this.who == that.who) && (this.when == that.when);
	}

	public int hashCode() {
		int hash = 17;
		hash = 31*hash + who.hashCode();
		hash = 31*hash + when.hashCode();
		hash = 31*hash + ((Double) amount).hashCode();
		return hash;
	}

	public static class WhoOrder implements Comparator<Transaction> {
		@Overrride
		public int compare(Transaction v, Transaction w) {
			return v.who.compareTo(w.who);
		}
	}
	public static class WhenOrder implements Comparator<Transaction> {
		@Overrride
		public int compare(Transaction v, Transaction w) {
			return v.when.compareTo(w.when);
		}
	}

	public static class HowMuchOrder implements Comparator<Transaction> {
		@Overrride
		public int compare(Transaction v, Transaction w) {
			if(v.amount < w.amount) {
				return -1;
			} else if(v.amount > w.amount) {
				return +1;
			} else {
				return 0;
			}
		}
	}





}