package com.digitalojt.web.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.digitalojt.web.entity.OperationLog;

/**
 * 操作履歴テーブルリポジトリー
 *
 * @author dotlife
 * 
 */
@Repository
public interface OperationLogRepository extends JpaRepository<OperationLog, Integer> {

	@Query("SELECT o FROM OperationLog o JOIN FETCH o.adminInfo "
		     + "WHERE (:userId IS NULL OR o.adminInfo.adminName LIKE %:userId%) "
		     + "AND (:operateType IS NULL OR o.operateType = :operateType) "
		     + "AND (:status IS NULL OR o.status = :status) "
		     + "AND ((:createDate IS NULL OR :updateDate IS NULL) OR o.createDate BETWEEN :createDate AND :updateDate)"
		     + "ORDER BY o.createDate ASC")
	List<OperationLog> findOperationLogs(
			@Param("userId") String userId,
			@Param("operateType") Integer operateType,
		    @Param("status") Integer status,
		    @Param("createDate") LocalDateTime createDate,
		    @Param("updateDate") LocalDateTime updateDate
		    );
	
	
	@Query("SELECT o FROM OperationLog o JOIN FETCH o.adminInfo a "
		     + "WHERE (:userId IS NULL OR a.adminName LIKE %:userId%) "
		     + "AND (:tableKey IS NULL OR o.tableKey = :tableKey) "
		     + "AND (:operateType IS NULL OR o.operateType = :operateType) "
		     + "AND (:operationDetails IS NULL OR o.operationDetails = :operationDetails) "
		     + "AND (:status IS NULL OR o.status = :status) "
		     + "AND (:createDate IS NULL OR :updateDate IS NULL OR o.createDate BETWEEN :createDate AND :updateDate) "
		     + "ORDER BY o.createDate DESC")
		List<OperationLog> findOperationLogsForIndex(
		    @Param("userId") String userId,
		    @Param("tableKey") String tableKey,
		    @Param("operateType") Integer operateType,
		    @Param("operationDetails") String operationDetails,
		    @Param("status") Integer status,
		    @Param("createDate") LocalDateTime createDate,
		    @Param("updateDate") LocalDateTime updateDate
		    );

}
