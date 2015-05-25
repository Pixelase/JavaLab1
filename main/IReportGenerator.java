package main;
public interface IReportGenerator<R, P> {
	public R generateReport(P parametr);
}
